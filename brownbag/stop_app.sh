#!/bin/bash
docker stack rm app
sleep 5
docker network rm app_nw