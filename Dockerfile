FROM mysql:8.0

# En las imágenes de Oracle Linux se usa microdnf en lugar de apt-get
USER root
RUN microdnf install -y netcat && microdnf clean all

# Exponemos el puerto de MySQL
EXPOSE 3306

# Un pequeño script para que Render crea que hay actividad
CMD ["sh", "-c", "mysqld & sleep 5; while true; do nc -z localhost 3306; sleep 30; done"]