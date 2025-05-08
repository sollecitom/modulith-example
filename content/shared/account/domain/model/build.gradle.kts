dependencies {
    api(projects.swissknifeCoreDomain)
    api(projects.swissknifeCorrelationCoreDomain)
    api(projects.swissknifeDddDomain)

    implementation(projects.swissknifeKotlinExtensions)
    implementation(projects.swissknifeCoreUtils)

    testImplementation(projects.swissknifeTestUtils)
    testImplementation(projects.swissknifeCoreTestUtils)
}