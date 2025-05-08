dependencies {
    testImplementation(projects.modulithExampleModulesDepositEventEndpointAdaptersDrivingHttpEndpoint)
    testImplementation(projects.modulithExampleModulesDepositEventEndpointAdaptersDrivingHttpSerializationJson)
    testImplementation(projects.modulithExampleModulesDepositEventEndpointDomainTestUtils)
    testImplementation(projects.modulithExampleContractsHttp)
    testImplementation(projects.swissknifeLoggingStandardSlf4jConfiguration)
    testImplementation(projects.swissknifeTestUtils)
    testImplementation(projects.pillarWebApiTestUtils)
    testImplementation(projects.swissknifeOpenapiCheckingTestUtils)
    testImplementation(projects.swissknifeCorrelationCoreTestUtils)
    testImplementation(projects.pillarCorrelationLoggingTestUtils)
    testImplementation(projects.swissknifeResourceUtils)
    testImplementation(projects.swissknifeOpenapiValidationHttp4kTestUtils)
}