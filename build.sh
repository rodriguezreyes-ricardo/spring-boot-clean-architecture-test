#!/usr/bin/env bash
set -e

# 1. Encontrar la ruta real de Java en Render
export JAVA_HOME=$(readlink -f $(which java) | sed "s:/bin/java::")
export PATH=$JAVA_HOME/bin:$PATH

# 2. Dar permisos al wrapper
chmod +x mvnw

# 3. Ejecutar la compilación
./mvnw clean install -DskipTests