import com.palantir.gradle.gitversion.VersionDetails
import sollecitom.plugins.conventions.task.jib.JibDockerBuildConvention

dependencies {
    api(projects.modulithExampleConfiguration)
    api(projects.swissknifeWebServiceDomain)
    api(projects.modulithExampleContractsHttp)
    api(projects.pillarMessagingConventions)

    implementation(projects.pillarAcmeBusinessDomain)
    implementation(projects.modulithExampleConfiguration)
    implementation(projects.pillarServiceLogging)
    implementation(projects.pillarPrometheusMicrometerRegistry)
    implementation(projects.swissknifeWebOpenapiUtils)
    implementation(projects.swissknifeSqlPostgresUtils)
    implementation(projects.swissknifePulsarUtils)
    implementation(projects.swissknifeKotlinExtensions)
    implementation(projects.pillarWebApiUtils)
    implementation(projects.swissknifeOpentelemetryCore)
    implementation(projects.swissknifeOpentelemetryExporterOltp)

    implementation(projects.modulithExampleModulesDepositEventEndpointModuleImplementation)
    implementation(projects.modulithExampleModulesPaymentCommandEndpointModuleImplementation)
}

apply<JibDockerBuildConvention>()

val serviceName = "modulith-example-service"
val dockerRemoteRepository: String by project.extra
val dockerBaseImageParam: String by project.extra
val gitVersion: VersionDetails by project.extra
val mainAppPort = "8081"
val healthAppPort = "8082"
val tmpVolume = "/tmp"
val maxRamPercentage = "70.000000"

configure<JibDockerBuildConvention.Extension> {
    serviceImageName = "$dockerRemoteRepository/$serviceName"
    starterClassFullyQualifiedName = "sollecitom.services.modulith_example.service.starter.StarterKt"
    dockerBaseImage = dockerBaseImageParam
    reproducibleBuild = true
    volumes = listOf(tmpVolume)
    tags = listOf(gitVersion.gitHashFull)
    args = listOf(
        "-Djava.security.egd=file:/dev/./urandom"
    )
    jvmFlags = listOf(
        "-XX:+UnlockExperimentalVMOptions",
        "-XX:+HeapDumpOnOutOfMemoryError",
        "-XX:HeapDumpPath=$tmpVolume/java_pid<pid>.hprof",
        "-XX:MaxRAMPercentage=$maxRamPercentage",
        "-XX:+UseZGC",
        "-XX:+AlwaysPreTouch",
        "-XX:+UseNUMA"
    )
    environment = mutableMapOf(
        "SERVICE_PORT" to mainAppPort,
        "HEALTH_PORT" to healthAppPort
    )
}