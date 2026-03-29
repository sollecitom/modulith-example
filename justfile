#!/usr/bin/env just --justfile

reset-all:
    git fetch origin && git reset --hard origin/main && git clean -f -d

push:
    git add -A && (git diff --quiet HEAD || git commit -am "WIP") && git push origin main

pull:
    git pull

# Jib 3.5.3 is incompatible with configuration cache (serializes Project), so jibDockerBuild runs separately
build:
    ./gradlew build && ./gradlew --no-configuration-cache jibDockerBuild containerBasedServiceTest

rebuild:
    ./gradlew --refresh-dependencies --rerun-tasks clean build && ./gradlew --no-configuration-cache --rerun-tasks jibDockerBuild containerBasedServiceTest

update-dependencies:
    ./gradlew versionCatalogUpdate

update-gradle:
    ./gradlew wrapper --gradle-version latest --distribution-type all

update-all:
    just update-dependencies && just update-gradle
