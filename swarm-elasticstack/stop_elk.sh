#!/bin/bash
docker stack rm elk
sleep 5
docker network rm elk_nw