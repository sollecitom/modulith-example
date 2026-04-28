#!/usr/bin/env just --justfile

set quiet

reset-all:
    git fetch origin && git reset --hard origin/main && git clean -f -d

push:
    git add -A && (git diff --quiet HEAD || git commit -am "WIP") && git push origin main

pull:
    git pull

# Jib 3.5.3 is incompatible with configuration cache (serializes Project), so jibDockerBuild runs separately
build:
    ./gradlew updateInternalCatalogVersions && ./gradlew build && ./gradlew --no-configuration-cache jibDockerBuild containerBasedServiceTest securityScan

license-audit:
    bash ../scripts/run-license-audit.sh modulith-example

license-audit-compact:
    bash ../scripts/run-license-audit.sh modulith-example --compact

generate-sbom:
    bash ../scripts/run-generate-sbom.sh modulith-example

cleanup:
    bash ../scripts/cleanup-maven-local.sh --repo-root . --keep 2 --max-age-days 14
    bash ../scripts/cleanup-docker-images.sh --keep 2 ghcr.io/sollecitom/modulith-example-service

update-internal-dependencies:
    ./gradlew updateInternalCatalogVersions

rebuild:
    ./gradlew --refresh-dependencies --rerun-tasks clean build && ./gradlew --no-configuration-cache --rerun-tasks jibDockerBuild containerBasedServiceTest securityScan

update-dependencies:
    ./gradlew versionCatalogUpdate

@update-gradle:
    ./scripts/update-gradle.sh

update-all:
    just update-internal-dependencies && just update-dependencies && just update-gradle

workflow +steps:
    bash ../scripts/run-just-workflow.sh {{steps}}
