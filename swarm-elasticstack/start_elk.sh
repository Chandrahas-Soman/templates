#!/bin/bash
docker network create --attachable --driver overlay elk_nw
sleep 5
env TAG="6.5.4" docker stack deploy -c docker-compose.yml elk
