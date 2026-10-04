plugins {
    id("sollecitom.kotlin-library-conventions")
}

dependencies {
    api(projects.sharedNatsAdapter)

    testImplementation(projects.sharedAccountDomainTestUtils)
    testImplementation(libs.swissknife.ddd.test.utils)
    testImplementation(libs.swissknife.core.test.utils)
    testImplementation(libs.swissknife.test.utils)
}
