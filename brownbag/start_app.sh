#!/bin/bash
docker network create --attachable --driver overlay app_nw
sleep 5
env VERSION="0.1.0" docker stack deploy -c docker-compose.yml app
