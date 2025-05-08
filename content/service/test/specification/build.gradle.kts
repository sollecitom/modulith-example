dependencies {
    api(projects.swissknifeWebServiceDomain)
    api(projects.pillarWebApiTestUtils)
    api(projects.swissknifeTestUtils)
    api(projects.swissknifeCorrelationCoreTestUtils)
    api(projects.swissknifeDddTestUtils)
    api(projects.swissknifeKotlinExtensions)
    api(projects.swissknifeSqlPostgresContainer)
    api(projects.swissknifeSqlPostgresUtils)
    api(projects.swissknifeWebOpenapiTestUtils)
    api(projects.modulithExampleMigratorPostgresDomain)
    api(platform(libs.http4k.bom))
    api(libs.http4k.client.apache.async)
    api(projects.pillarServiceLogging)
    api(projects.swissknifeOpentelemetryTestContainerUtils)

    api(projects.modulithExampleModulesDepositEventEndpointModuleTestSpecification)
    api(projects.modulithExampleModulesPaymentCommandEndpointModuleTestSpecification)

    implementation(projects.modulithExampleSharedSerializationAvro)
    implementation(projects.swissknifePulsarJsonSerialization)
}