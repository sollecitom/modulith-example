import com.palantir.gradle.gitversion.VersionDetails
import groovy.lang.Closure

plugins {
    alias(libs.plugins.com.palantir.git.version)
    id("sollecitom.dependency-update-conventions")
    id("sollecitom.aggregate-test-metrics-conventions")
}

val versionDetails: Closure<VersionDetails> by extra
extra["gitVersion"] = versionDetails()
extra["dockerRemoteRepository"] = findProperty("dockerRemoteRepository") ?: "ghcr.io/sollecitom"
extra["dockerBaseImageParam"] = findProperty("dockerBaseImageParam") ?: "eclipse-temurin:25-jre-noble"
