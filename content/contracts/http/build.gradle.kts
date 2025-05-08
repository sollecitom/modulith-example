dependencies {
    api(projects.swissknifeOpenapiProvider)
    api(projects.swissknifeOpenapiParser)
    api(projects.pillarHttpApiConventions)

    runtimeOnly(projects.modulithExampleModulesDepositEventEndpointAdaptersDrivingHttpEndpoint)
    runtimeOnly(projects.modulithExampleModulesPaymentCommandEndpointAdaptersDrivingHttpEndpoint)
    runtimeOnly(projects.pillarJsonSerializationWebApi)

    testImplementation(projects.swissknifeLoggingStandardSlf4jConfiguration)
    testImplementation(projects.swissknifeTestUtils)
    testImplementation(projects.pillarWebApiTestUtils)
    testImplementation(projects.swissknifeOpenapiCheckingTestUtils)
    testImplementation(projects.swissknifeCorrelationCoreTestUtils)
    testImplementation(projects.pillarCorrelationLoggingTestUtils)
    testImplementation(projects.swissknifeResourceUtils)
    testImplementation(projects.swissknifeOpenapiValidationHttp4kTestUtils)
    testImplementation(projects.pillarOpenApiRules)
}