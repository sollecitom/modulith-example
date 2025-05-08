dependencies {
    api(projects.swissknifeServiceDomain)
    api(projects.swissknifePulsarUtils)

    implementation(projects.modulithExampleModulesDepositEventEndpointAdaptersDrivingHttpEndpoint)
    implementation(projects.modulithExampleModulesDepositEventEndpointAdaptersDrivenPulsar)
    implementation(projects.swissknifeLoggerCore)

    implementation(projects.swissknifeKotlinExtensions)
}