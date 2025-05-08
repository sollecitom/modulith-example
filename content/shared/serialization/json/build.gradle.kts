dependencies {
    api(projects.modulithExampleSharedAccountDomainModel)
    api(projects.pillarJsonSerializationCore)
    api(projects.pillarJsonSerializationDdd)

    implementation(projects.pillarJsonSerializationCorrelationCore)

    testImplementation(projects.modulithExampleSharedAccountDomainTestUtils)
    testImplementation(projects.pillarJsonSerializationTestUtils)
    testImplementation(projects.swissknifeCorrelationCoreTestUtils)
    testImplementation(projects.swissknifeCoreTestUtils)
    testImplementation(projects.swissknifeTestUtils)
}
