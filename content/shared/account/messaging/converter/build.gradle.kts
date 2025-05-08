dependencies {
    api(projects.modulithExampleSharedAccountDomainModel)
    api(projects.modulithExampleSharedAccountSerializationAvro)
    api(projects.modulithExampleSharedMessagingTopics)
    api(projects.swissknifeMessagingDomain)

    implementation(projects.modulithExampleSharedSerializationAvro)
    implementation(projects.pillarMessagingConventions)
}