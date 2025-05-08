dependencies {
    api(projects.modulithExampleSharedAccountDomainModel)
    api(projects.swissknifeCoreDomain)
    api(projects.swissknifeCorrelationCoreDomain)
    api(projects.swissknifeDddDomain)

    implementation(projects.swissknifeKotlinExtensions)
    implementation(projects.swissknifeCoreUtils)

    testImplementation(projects.modulithExampleSharedAccountDomainTestUtils)
    testImplementation(projects.swissknifeTestUtils)
    testImplementation(projects.swissknifeCoreTestUtils)
    testImplementation(projects.swissknifeDddTestUtils)
}