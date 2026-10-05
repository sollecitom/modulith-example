plugins {
    id("sollecitom.kotlin-library-conventions")
}

dependencies {
    api(projects.sharedNatsAdapter)

    testImplementation(projects.sharedAccountDomainTestUtils)
    testImplementation(libs.swissknife.core.test.utils)
}
