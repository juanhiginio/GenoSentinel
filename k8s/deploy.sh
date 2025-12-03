#!/usr/bin/env bash
set -euo pipefail


DOCKER_USER=${DOCKER_USER:-"juanhiginio"}
TAG=${TAG:-"1.0"}


echo "Construyendo imagenes..."
# Django
docker build -t ${DOCKER_USER}/genomica:${TAG} ./microservicioGenomica
# Nest
docker build -t ${DOCKER_USER}/clinica:${TAG} ./microservicioClinica
# Spring
docker build -t ${DOCKER_USER}/gateway:${TAG} ./microservicioGateway


# Push
docker push ${DOCKER_USER}/genomica:${TAG}
docker push ${DOCKER_USER}/clinica:${TAG}
docker push ${DOCKER_USER}/gateway:${TAG}


# Aplicar manifiestos
kubectl apply -f k8s/secrets/db-secrets.yaml
kubectl apply -f k8s/databases/pvc-genomica.yaml
kubectl apply -f k8s/databases/pvc-clinica.yaml
kubectl apply