import com.palantir.gradle.gitversion.VersionDetails
import groovy.lang.Closure

plugins {
    alias(libs.plugins.com.palantir.git.version)
    alias(libs.plugins.sollecitom.dependency.update.conventions)
    alias(libs.plugins.sollecitom.aggregate.test.metrics.conventions)
    alias(libs.plugins.sollecitom.kotlin.library.conventions) apply false
    alias(libs.plugins.sollecitom.jib.docker.build.conventions) apply false
    alias(libs.plugins.sollecitom.container.based.service.test.conventions) apply false
    alias(libs.plugins.sollecitom.security.scan.conventions) apply false
}

@Suppress("UNCHECKED_CAST")
val versionDetails = extra["versionDetails"] as Closure<VersionDetails>
extra["gitVersion"] = versionDetails()
extra["dockerRemoteRepository"] = findProperty("dockerRemoteRepository") ?: "ghcr.io/sollecitom"
extra["dockerBaseImageParam"] = findProperty("dockerBaseImageParam") ?: "eclipse-temurin:25-jre-noble"
