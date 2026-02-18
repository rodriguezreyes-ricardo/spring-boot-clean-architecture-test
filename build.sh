#!/usr/bin/env bash
# Abortar si hay un error
set -o errexit

# Configurar Java usando asdf
asdf install java openjdk-17
asdf local java openjdk-17

# Dar permisos y ejecutar Maven
chmod +x mvnw
./mvnw clean install -DskipTests