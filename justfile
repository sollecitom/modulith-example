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
