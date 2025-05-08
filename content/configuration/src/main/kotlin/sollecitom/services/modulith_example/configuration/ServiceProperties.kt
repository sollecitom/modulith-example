package sollecitom.services.modulith_example.configuration

import sollecitom.libs.swissknife.configuration.utils.instanceGroupName
import sollecitom.libs.swissknife.configuration.utils.instanceNodeName
import sollecitom.libs.swissknife.lens.core.extensions.base.javaURI
import sollecitom.libs.swissknife.lens.core.extensions.naming.name
import sollecitom.libs.swissknife.lens.core.extensions.networking.healthPort
import sollecitom.libs.swissknife.lens.core.extensions.networking.servicePort
import sollecitom.libs.swissknife.lens.core.extensions.security.password
import sollecitom.libs.swissknife.sql.domain.SqlConnectionOptions
import org.http4k.config.Environment
import org.http4k.config.EnvironmentKey

object ServiceProperties {

    val servicePort get() = EnvironmentKey.servicePort
    val healthPort get() = EnvironmentKey.healthPort

    val instanceNodeName get() = EnvironmentKey.instanceNodeName
    val instanceGroupName get() = EnvironmentKey.instanceGroupName
    const val pulsarConfigurationRoot = "pulsar.configuration"
    val pulsarBrokerUrl = EnvironmentKey.javaURI().required("pulsar.broker.url")

    val openTelemetryEndpointUrl = EnvironmentKey.javaURI().required("opentelemetry.endpoint.url")
}