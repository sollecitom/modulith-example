dependencies {
    api(projects.swissknifeServiceDomain)
    api(projects.swissknifePulsarUtils)

    implementation(projects.modulithExampleModulesAccountEventProcessorAdaptersDrivingPulsar)
    implementation(projects.modulithExampleModulesAccountEventProcessorAdaptersDrivenPulsar)
    implementation(projects.modulithExampleModulesAccountEventProcessorApplicationModel)
    implementation(projects.swissknifeLoggerCore)
    implementation(projects.pillarMessagingDomain)

    implementation(projects.swissknifeKotlinExtensions)
}