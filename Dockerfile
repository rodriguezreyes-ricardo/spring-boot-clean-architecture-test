# Usamos MariaDB como base
FROM mariadb:10.11

# 1. Evitamos preguntas interactivas durante la instalación
ENV DEBIAN_FRONTEND=noninteractive

# 2. Instalamos phpMyAdmin y dependencias
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
    phpmyadmin \
    php-mbstring \
    php-zip \
    php-gd \
    php-json \
    php-curl \
    && rm -rf /var/lib/apt/lists/*

# 3. Configuración de phpMyAdmin para conectar internamente
RUN echo "<?php \
\$cfg['Servers'][1]['host'] = '127.0.0.1'; \
\$cfg['Servers'][1]['compress'] = false; \
\$cfg['Servers'][1]['AllowNoPassword'] = false; \
?>" > /etc/phpmyadmin/config.inc.php

# 4. Exponemos puertos
EXPOSE 3306
EXPOSE 80

# 5. COMANDO CMD MEJORADO:
# Forzamos el puerto y la dirección para que Render lo detecte
# Usamos 'exec gosu mysql' o simplemente 'runuser' para que no sea root
CMD ["sh", "-c", "mariadbd --user=mysql --bind-address=0.0.0.0 --port=3306 & sleep 10; php -S 0.0.0.0:80 -t /usr/share/phpmyadmin"]