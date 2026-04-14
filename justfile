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
    ./gradlew build && ./gradlew --no-configuration-cache jibDockerBuild containerBasedServiceTest securityScan

rebuild:
    ./gradlew --refresh-dependencies --rerun-tasks clean build && ./gradlew --no-configuration-cache --rerun-tasks jibDockerBuild containerBasedServiceTest securityScan

update-dependencies:
    ./gradlew versionCatalogUpdate

@update-gradle:
    #!/usr/bin/env bash
    set -euo pipefail
    ./gradlew wrapper --gradle-version latest --distribution-type all
    DIST_URL=$(grep distributionUrl gradle/wrapper/gradle-wrapper.properties | cut -d= -f2 | sed 's/\\//g')
    CHECKSUM=$(curl -sL "${DIST_URL}.sha256")
    sed -i '' "s/distributionSha256Sum=.*/distributionSha256Sum=${CHECKSUM}/" gradle/wrapper/gradle-wrapper.properties
    echo "Updated wrapper checksum: ${CHECKSUM}"

update-all:
    just update-dependencies && just update-gradle
