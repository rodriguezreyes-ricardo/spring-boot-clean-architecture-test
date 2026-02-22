#!/bin/bash
set -e

# Arrancar MariaDB en background
mkdir -p /run/mysqld
chown -R mysql:mysql /run/mysqld
mysqld --initialize-insecure --user=mysql --datadir=/var/lib/mysql
mysqld_safe --user=mysql &

# Esperar que MySQL esté listo
echo "Esperando que MySQL arranque..."
sleep 10

# Arrancar phpMyAdmin en background
php -S 0.0.0.0:8081 -t /usr/share/phpmyadmin &

# Finalmente, arrancar Tomcat en foreground
catalina.sh run