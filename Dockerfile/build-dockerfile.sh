#!/bin/bash

# Default configuration
ENV=1.0.1
REGISTRY=acimmino
IMAGE_NAME=doctor-fis-service
PLATFORMS=linux/amd64,linux/arm64,linux/arm/v7


docker login
docker buildx use multiplatform
# Build for AMD64/ARM64 & push to private registry
docker buildx build --platform ${PLATFORMS} \
                    --tag ${REGISTRY}/${IMAGE_NAME}:${ENV} \
                    --build-arg UID=1001 --build-arg GID=1001 \
                    --build-arg BUILD_DATE=$(date -u +'%Y-%m-%dT%H:%M:%SZ') \
                    --build-arg BUILD_VERSION="1.0" \
                    -f Dockerfile . --push
docker pull ${REGISTRY}/${IMAGE_NAME}:${ENV}