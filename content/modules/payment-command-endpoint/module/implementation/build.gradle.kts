dependencies {
    api(projects.swissknifeServiceDomain)
    api(projects.swissknifePulsarUtils)

    implementation(projects.modulithExampleModulesPaymentCommandEndpointAdaptersDrivingHttpEndpoint)
    implementation(projects.modulithExampleModulesPaymentCommandEndpointAdaptersDrivenPulsar)
    implementation(projects.modulithExampleModulesPaymentCommandEndpointAdaptersDrivenNats)
    implementation(projects.swissknifeLoggerCore)

    implementation(projects.swissknifeKotlinExtensions)
}