#!/bin/bash
mkdir postgres-data

docker compose -f "docker-compose.yml" up -d --build