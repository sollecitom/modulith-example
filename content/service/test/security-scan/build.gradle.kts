plugins {
    id("sollecitom.kotlin-library-conventions")
    id("sollecitom.security-scan-conventions")
}

import sollecitom.plugins.conventions.task.security.scan.SecurityScanConvention
import sollecitom.plugins.conventions.task.security.scan.securityScanImplementation

configure<SecurityScanConvention.Extension> {
    starterModuleName = projects.serviceStarter.name
    imageName = "ghcr.io/sollecitom/modulith-example-service"
}

dependencies {
    securityScanImplementation(libs.swissknife.security.scan)
}
