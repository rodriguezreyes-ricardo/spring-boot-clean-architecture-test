# Usamos la versión de mysql que permite instalar paquetes fácilmente
FROM mysql:8.0

# 1. Forzamos la instalación de un servidor web mínimo (Python) 
# usando el gestor de paquetes de Oracle Linux (dnf) que es el que trae esta imagen
USER root
RUN dnf install -y python3 && dnf clean all

# 2. Exponemos los puertos
EXPOSE 3306
EXPOSE 80

# 3. Comando de inicio: 
# Arranca MySQL y un servidor web en el puerto 80 para engañar a Render
CMD ["sh", "-c", "mysqld & sleep 10; python3 -m http.server 80"]