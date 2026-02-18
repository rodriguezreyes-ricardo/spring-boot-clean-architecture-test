# MariaDB es un reemplazo directo de MySQL y usa Debian (más fácil de configurar)
FROM mariadb:10.11

# 1. Instalamos un servidor web ultra ligero (Python)
RUN apt-get update && apt-get install -y python3 && rm -rf /var/lib/apt/lists/*

# 2. Variables de entorno por defecto (Render las sobreescribirá)
ENV MARIADB_ROOT_PASSWORD=R3do-docente
ENV MARIADB_DATABASE=docente
ENV MARIADB_USER=docente
ENV MARIADB_PASSWORD=R3do-docente

# 3. Exponemos el puerto de la DB y el puerto 80 para engañar a Render
EXPOSE 3306
EXPOSE 80

# 4. El comando "mágico": arranca la DB y el servidor web para que Render vea "Live"
CMD ["sh", "-c", "docker-entrypoint.sh mariadbd & sleep 10; python3 -m http.server 80"]