plugins {
    id("sollecitom.kotlin-library-conventions")
}

dependencies {
    api(libs.swissknife.nats.client)
    api(libs.swissknife.core.utils)
    api(libs.swissknife.correlation.core.domain)
    api(projects.modulesPaymentCommandEndpointDomainModel)
}