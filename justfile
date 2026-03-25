#!/usr/bin/env just --justfile

reset-all:
    git fetch origin && git reset --hard origin/main && git clean -f -d

push:
    git add -A && (git diff --quiet HEAD || git commit -am "WIP") && git push origin main

pull:
    git pull

build:
    ./gradlew build jibDockerBuild containerBasedServiceTest

rebuild:
    ./gradlew --refresh-dependencies --rerun-tasks clean build jibDockerBuild containerBasedServiceTest

update-dependencies:
    ./gradlew versionCatalogUpdate

update-gradle:
    ./gradlew wrapper --gradle-version latest --distribution-type all

update-all:
    just update-dependencies && just update-gradle
