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
FROM tomcat:9.0-jdk17

# Instalar MariaDB y PHP para phpMyAdmin
USER root
RUN apt-get update && apt-get install -y \
    mariadb-server \
    php php-mbstring php-zip php-gd php-json php-curl \
    wget unzip curl \
    && rm -rf /var/lib/apt/lists/*

# Variables de entorno de MariaDB
ENV MYSQL_ROOT_PASSWORD=root123
ENV MYSQL_USER=usuario
ENV MYSQL_PASSWORD=1234
ENV MYSQL_DATABASE=mi_db

# Copiar WAR
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Copiar init.sql para inicializar la DB
COPY init.sql /docker-entrypoint-initdb.d/init.sql

# Copiar phpMyAdmin
RUN wget https://www.phpmyadmin.net/downloads/phpMyAdmin-latest-all-languages.zip -O /tmp/pma.zip && \
    unzip /tmp/pma.zip -d /usr/share/ && \
    mv /usr/share/phpMyAdmin-*-all-languages /usr/share/phpmyadmin && \
    rm /tmp/pma.zip

# Exponer puertos
EXPOSE 8080 8081

# Script de arranque
COPY docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

# CMD final
CMD ["/usr/local/bin/docker-entrypoint.sh"]