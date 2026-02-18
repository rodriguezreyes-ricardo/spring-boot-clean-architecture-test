# Usamos MariaDB como base porque es lo más pesado de instalar
FROM mariadb:10.11

# 1. Instalamos phpMyAdmin y PHP
RUN apt-get update && apt-get install -y \
    phpmyadmin \
    php-mbstring \
    php-zip \
    php-gd \
    php-json \
    php-curl \
    && rm -rf /var/lib/apt/lists/*

# 2. Configuramos phpMyAdmin para que apunte al localhost (donde estará MariaDB)
RUN echo "<?php \
\$cfg['Servers'][1]['host'] = 'localhost'; \
\$cfg['Servers'][1]['compress'] = false; \
\$cfg['Servers'][1]['AllowNoPassword'] = false; \
?>" > /etc/phpmyadmin/config.inc.php

# 3. Exponemos el puerto 80 (para la web de phpMyAdmin)
# MariaDB (3306) NO se expone, así que solo será accesible internamente
EXPOSE 80

# 4. Script de inicio para arrancar ambos servicios
CMD ["sh", "-c", "docker-entrypoint.sh mariadbd & php -S 0.0.0.0:80 -t /usr/share/phpmyadmin"]