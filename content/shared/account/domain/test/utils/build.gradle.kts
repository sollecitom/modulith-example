dependencies {
    api(projects.modulithExampleSharedAccountDomainModel)
    api(projects.swissknifeCoreUtils)
    api(projects.swissknifeCorrelationCoreTestUtils)
    api(projects.swissknifeDddTestUtils)
    api(projects.swissknifeTestUtils)

    implementation(projects.swissknifeLoggerCore)
    implementation(projects.swissknifeConfigurationUtils)
}