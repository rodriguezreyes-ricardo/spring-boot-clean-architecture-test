# =========================
# ETAPA 1: Build con Maven
# =========================
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# =========================
# ETAPA 2: Tomcat runtime
# =========================
FROM tomcat:9.0-jdk17-openjdk-slim

# Elimina apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia el WAR compilado
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Cambiar puerto dinámicamente según variable PORT
CMD sed -i "s/port=\"8080\"/port=\"${PORT}\"/" /usr/local/tomcat/conf/server.xml && catalina.sh run