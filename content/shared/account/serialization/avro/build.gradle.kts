dependencies {
    api(projects.modulithExampleSharedAccountDomainModel)
    api(projects.swissknifeCorrelationCoreDomain)
    api(projects.pillarAvroSerializationCore)
    api(projects.pillarAvroSerializationDdd)
    api(projects.swissknifeAvroSerializationUtils)
    api(projects.swissknifeAvroSchemaCatalogueDomain)
    api(projects.pillarMessagingAvro)

    implementation(projects.modulithExampleSharedSerializationAvro)
    implementation(projects.pillarAvroSerializationCorrelationCore)
    implementation(projects.swissknifeKotlinExtensions)
    implementation(projects.acmeSchemaCatalogueAvroModulithExample)
    implementation(projects.acmeSchemaCatalogueAvroCommon)

    testImplementation(projects.modulithExampleSharedAccountDomainTestUtils)
    testImplementation(projects.pillarAvroSerializationTestUtils)
    testImplementation(projects.swissknifeCorrelationCoreTestUtils)
    testImplementation(projects.swissknifeAvroSchemaCatalogueTestUtils)
    testImplementation(projects.swissknifeDddTestUtils)
    testImplementation(projects.swissknifeCoreTestUtils)
    testImplementation(projects.swissknifeTestUtils)
}
