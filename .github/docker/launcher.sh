#!/usr/bin/env bash

# -e: exit when any command fails
# -x: all executed commands are printed to the terminal
# -o pipefail: prevents errors in a pipeline from being masked
set -exo pipefail

# GITHUB_WORKSPACE is set by actions/checkout@v3

export DOCKER_WORKSPACE_DIR="/root/$PROJECT_NAME"

docker run \
           --memory-swap -1 \
           --env WORKSPACE_DIR=$DOCKER_WORKSPACE_DIR \
           --env-file .github/docker/docker.env \
           -v "${GITHUB_WORKSPACE}:${DOCKER_WORKSPACE_DIR}" \
           $DOCKER_IMAGE \
           /bin/bash -c "${DOCKER_WORKSPACE_DIR}/.github/docker/script.sh"

exit 0
