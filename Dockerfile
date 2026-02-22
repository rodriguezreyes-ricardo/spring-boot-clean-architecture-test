# -----------------------------
# ETAPA 1: Build WAR con Maven
# -----------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# -----------------------------
# ETAPA 2: Contenedor final
# -----------------------------
FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

# Instalar dependencias: OpenJDK, Tomcat, MariaDB y PHP
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    curl \
    unzip \
    wget \
    php php-mbstring php-zip php-gd php-json php-curl \
    mariadb-server \
    tomcat9 tomcat9-admin tomcat9-common \
    supervisor \
    && rm -rf /var/lib/apt/lists/*

# Variables de entorno para MariaDB
ENV MYSQL_ROOT_PASSWORD=root123
ENV MYSQL_USER=usuario
ENV MYSQL_PASSWORD=1234
ENV MYSQL_DATABASE=mi_db

# Copiar script SQL de inicialización
COPY init.sql /docker-entrypoint-initdb.d/init.sql

# Copiar WAR generado a Tomcat
COPY --from=build /app/target/*.war /var/lib/tomcat9/webapps/ROOT.war

# Copiar configuración de supervisor
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# Exponer solo los puertos públicos
# 8080 -> Tomcat
# 8081 -> phpMyAdmin
EXPOSE 8080 8081

# Arrancar supervisord
CMD ["/usr/bin/supervisord", "-c", "/etc/supervisor/conf.d/supervisord.conf"]