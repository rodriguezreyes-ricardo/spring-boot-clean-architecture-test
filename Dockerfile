FROM mysql:8.0

# Definimos el puerto de MySQL
EXPOSE 3306

# Instalamos netcat para que Render pueda detectar el puerto abierto 
# aunque no sea HTTP
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*