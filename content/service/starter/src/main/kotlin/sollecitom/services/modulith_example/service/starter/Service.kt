package sollecitom.services.modulith_example.service.starter

import kotlinx.coroutines.coroutineScope
import org.apache.pulsar.client.api.PulsarClient
import org.http4k.config.Environment
import sollecitom.libs.pillar.acme.business.domain.Example
import sollecitom.libs.pillar.acme.conventions.CompanyConventions
import sollecitom.libs.pillar.prometheus.micrometer.prometheusMeterRegistry
import sollecitom.libs.pillar.service.logging.logServiceStarted
import sollecitom.libs.pillar.service.logging.logServiceStopped
import sollecitom.libs.pillar.web.api.utils.api.EndpointHttpDrivingAdapter
import sollecitom.libs.pillar.web.api.utils.api.create
import sollecitom.libs.swissknife.configuration.utils.configurationPropertiesUnderRoot
import sollecitom.libs.swissknife.core.domain.lifecycle.closeReportingFailure
import sollecitom.libs.swissknife.core.domain.lifecycle.stopReportingFailure
import sollecitom.libs.swissknife.pulsar.utils.PulsarClientSettings
import sollecitom.libs.swissknife.core.domain.text.Name
import sollecitom.libs.swissknife.core.utils.CoreDataGenerator
import sollecitom.libs.swissknife.core.utils.provider
import sollecitom.libs.swissknife.logger.core.loggable.Loggable
import sollecitom.libs.swissknife.openapi.provider.provider.OpenApiProvider
import sollecitom.libs.swissknife.openapi.provider.provider.standard
import sollecitom.libs.swissknife.pulsar.utils.readiness.PulsarReadinessCheck
import sollecitom.libs.swissknife.readiness.domain.ClientAndCheck
import sollecitom.libs.swissknife.readiness.domain.checkReadiness
import sollecitom.libs.swissknife.readiness.domain.ifFailed
import sollecitom.libs.swissknife.service.domain.ServiceInfo
import sollecitom.libs.swissknife.service.domain.fromEnvironment
import sollecitom.libs.swissknife.service.domain.ServiceModule
import sollecitom.libs.swissknife.service.domain.ServiceModuleWithHttpDrivingAdapter
import sollecitom.libs.swissknife.service.readiness.http4k.http4kReadinessCheck
import sollecitom.libs.swissknife.web.api.utils.api.HealthHttpDrivingAdapter
import sollecitom.libs.swissknife.web.openapi.utils.OpenApiEndpoint
import sollecitom.libs.swissknife.web.service.domain.WebInterface
import sollecitom.libs.swissknife.web.service.domain.WebService
import sollecitom.services.modulith_example.configuration.ServiceProperties
import sollecitom.services.modulith_example.modules.deposit_event_endpoint.module.implementation.depositEventEndpoint
import sollecitom.services.modulith_example.modules.payment_command_endpoint.module.implementation.paymentCommandEndpoint
import java.util.concurrent.TimeUnit.SECONDS

class Service(private val environment: Environment, coreDataGenerators: CoreDataGenerator) :
    WebService,
    CoreDataGenerator by coreDataGenerators,
    CompanyConventions by Example,
    OpenApiProvider by OpenApiProvider.standard {

    constructor(environment: Environment) : this(environment = environment, coreDataGenerators = CoreDataGenerator.provider(environment))

    //    private val openTelemetry = OpenTelemetryModule.withOpenTelemetryEndpointUrl(endpointUrl = openTelemetryEndpointUrl(environment))
    private val meterRegistry = prometheusMeterRegistry()
    private val instanceInfo = ServiceInfo.fromEnvironment(environment, serviceName)
    private val pulsarClient = pulsarClient(environment)

    private val modules = with(ServiceModule) {
        setOf(
            depositEventEndpoint(pulsarClient.value, instanceInfo, environment),
            paymentCommandEndpoint(pulsarClient.value, instanceInfo, environment),
        )
    }

    private val healthHttpDrivingAdapter = HealthHttpDrivingAdapter(environment = environment, meterRegistry = meterRegistry, checks = setOf(pulsarClient.http4kReadinessCheck))
    private val mainHttpDrivingAdapter = EndpointHttpDrivingAdapter.create(endpoints = httpEndpoints, environment = environment, meterRegistry = meterRegistry)
    override val webInterface by lazy { WebInterface.local(port = mainHttpDrivingAdapter.port, healthPort = healthHttpDrivingAdapter.port) }

    override suspend fun start() {

        ensureAllDrivenAdaptersAreAvailable()
        modules.sortedBy { it.name.value }.forEach { module ->
            module.start()
            logger.info { "Started module '${module.name.value}'" }
        }
        mainHttpDrivingAdapter.start()
        healthHttpDrivingAdapter.start()
        logServiceStarted()
    }

    override suspend fun stop() {

        logger.info { "${instanceInfo.name.value} instance with ID ${instanceInfo.instanceId.stringValue} is shutting down" }
        try {
            stopReportingFailure("the main HTTP driving adapter") { mainHttpDrivingAdapter.stop() }
            modules.sortedByDescending { it.name.value }.forEach { module -> stopReportingFailure("module '${module.name.value}'") { module.stop() } }
            stopReportingFailure("the health HTTP driving adapter") { healthHttpDrivingAdapter.stop() }
        } finally {
            closeReportingFailure("Pulsar client") { pulsarClient.value.close() }
            closeReportingFailure("meter registry") { meterRegistry.close() }
        }
        logServiceStopped()
    }

    private val List<ServiceModuleWithHttpDrivingAdapter>.httpEndpoints get() = flatMap(ServiceModuleWithHttpDrivingAdapter::httpEndpoints).toSet()
    private val httpEndpoints get() = modules.filterIsInstance<ServiceModuleWithHttpDrivingAdapter>().httpEndpoints + OpenApiEndpoint(openApiLocation)

    private suspend fun ensureAllDrivenAdaptersAreAvailable() = coroutineScope {

        pulsarClient.checkReadiness().ifFailed { error(it.reason) }
    }

    private fun pulsarClient(environment: Environment): ClientAndCheck<PulsarClient> {

        val pulsarBrokerServiceUrl = ServiceProperties.pulsarBrokerUrl(environment)
        val config = environment.configurationPropertiesUnderRoot(root = ServiceProperties.pulsarConfigurationRoot, knownNames = PulsarClientSettings.names)
        val client = PulsarClient.builder()
            .connectionTimeout(30, SECONDS)
            .operationTimeout(30, SECONDS)
            .serviceUrl(pulsarBrokerServiceUrl.toString()).loadConf(config).build()
        val readinessCheck = PulsarReadinessCheck(client)
        return ClientAndCheck(client, readinessCheck)
    }

    companion object : Loggable() {

        val serviceName = "modulith-example-service".let(::Name)
    }
}