plugins {
    id("sollecitom.kotlin-library-conventions")
}

dependencies {
    api(libs.swissknife.messaging.domain)
    api(libs.swissknife.pulsar.utils)
    api(libs.swissknife.core.utils)
    api(libs.swissknife.correlation.core.domain)
    api(projects.sharedAccountMessagingAdapters)
}