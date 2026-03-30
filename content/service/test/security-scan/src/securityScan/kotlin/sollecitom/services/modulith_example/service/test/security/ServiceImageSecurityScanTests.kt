package sollecitom.services.modulith_example.service.test.security

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import sollecitom.libs.swissknife.security.scan.assertThatImage
import sollecitom.libs.swissknife.security.scan.hasNoUnacceptableVulnerabilities

@TestInstance(PER_CLASS)
class ServiceImageSecurityScanTests {

    @Test
    fun `the service Docker image has no unacceptable vulnerabilities`() {

        val imageName = System.getProperty("securityScan.imageName")
            ?: error("securityScan.imageName system property is required")

        assertThatImage(imageName).hasNoUnacceptableVulnerabilities()
    }
}
