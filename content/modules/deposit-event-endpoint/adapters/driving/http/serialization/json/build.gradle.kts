dependencies {
    api(projects.modulithExampleModulesDepositEventEndpointDomainModel)
    api(projects.modulithExampleSharedSerializationJson)
    api(projects.pillarJsonSerializationCore)
    api(projects.pillarJsonSerializationDdd)

    implementation(projects.pillarJsonSerializationCorrelationCore)
    implementation(projects.pillarJsonSerializationDdd)
    implementation(projects.acmeSchemaCatalogueJsonCommon)

    testImplementation(projects.modulithExampleSharedAccountDomainTestUtils)
    testImplementation(projects.pillarJsonSerializationTestUtils)
    testImplementation(projects.swissknifeDddTestUtils)
    testImplementation(projects.swissknifeCorrelationCoreTestUtils)
    testImplementation(projects.swissknifeCoreTestUtils)
    testImplementation(projects.swissknifeTestUtils)
}
