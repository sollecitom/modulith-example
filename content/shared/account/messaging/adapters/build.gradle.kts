dependencies {
    api(projects.swissknifeMessagingDomain)
    api(projects.swissknifePulsarUtils)
    api(projects.modulithExampleSharedAccountDomainModel)

    implementation(projects.modulithExampleSharedAccountMessagingConverter)
    implementation(projects.modulithExampleSharedAccountSerializationAvro)
    implementation(projects.swissknifePulsarMessagingAdapter)
    implementation(projects.pillarMessagingPulsarAvro)
    implementation(projects.swissknifePulsarMessagingAdapter)
    implementation(projects.pillarMessagingConventions)
    implementation(projects.pillarCorrelationLoggingUtils)
    implementation(projects.swissknifeLoggerCore)
    implementation(projects.modulithExampleSharedSerializationAvro)
    implementation(projects.pillarMessagingConventions)
}