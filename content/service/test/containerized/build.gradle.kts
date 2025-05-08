import sollecitom.plugins.conventions.task.container.test.ContainerBasedServiceTestConvention
import sollecitom.plugins.conventions.task.container.test.containerBasedServiceTestImplementation

apply<ContainerBasedServiceTestConvention>()
configure<ContainerBasedServiceTestConvention.Extension> {
    starterModuleName = projects.modulithExampleServiceStarter.name
}

dependencies {
    containerBasedServiceTestImplementation(projects.modulithExampleServiceTestSpecification)
    containerBasedServiceTestImplementation(projects.modulithExampleServiceStarter)
    containerBasedServiceTestImplementation(projects.modulithExampleConfiguration)
    containerBasedServiceTestImplementation(projects.swissknifeTestContainersUtils)
    containerBasedServiceTestImplementation(projects.swissknifeCoreTestUtils)
    containerBasedServiceTestImplementation(projects.pillarAcmeBusinessDomain)
    containerBasedServiceTestImplementation(projects.pillarMessagingConventions)
    containerBasedServiceTestImplementation(projects.pillarWebApiTestUtils)
}