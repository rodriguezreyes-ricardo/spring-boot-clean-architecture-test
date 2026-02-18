FROM mysql:8.0

# 1. Cambiamos a Debian para asegurar que apt-get funcione
# Instalamos python3 para crear un servidor web falso en un segundo
RUN apt-get update && apt-get install -y python3 && rm -rf /var/lib/apt/lists/*

# 2. Exponemos el puerto de MySQL y el puerto 80 para Render
EXPOSE 3306
EXPOSE 80

# 3. El comando de inicio mágico:
# Arranca MySQL en segundo plano (&)
# Y luego arranca un servidor web ultra-simple en el puerto 80
CMD ["sh", "-c", "mysqld & python3 -m http.server 80"]