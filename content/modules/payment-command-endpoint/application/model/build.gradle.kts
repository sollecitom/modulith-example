dependencies {
    api(projects.modulithExampleModulesPaymentCommandEndpointDomainModel)
    api(projects.swissknifeCorrelationCoreDomain)
    api(projects.swissknifeServiceDomain)
    api(projects.swissknifePulsarUtils)
    api(projects.swissknifeMessagingDomain)

    implementation(projects.modulithExampleSharedAccountMessagingConverter)
    implementation(projects.swissknifeLoggerCore)
}