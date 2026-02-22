FROM eclipse-temurin:17-jdk-jammy

ENV DEBIAN_FRONTEND=noninteractive

# Instalar MariaDB, Apache, PHP, phpMyAdmin y supervisor
RUN apt-get update && apt-get install -y \
    mariadb-server \
    apache2 \
    php \
    libapache2-mod-php \
    php-mysql \
    php-mbstring \
    php-zip \
    php-gd \
    php-json \
    php-curl \
    phpmyadmin \
    supervisor \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Configurar MariaDB
RUN mkdir -p /var/run/mysqld && chown -R mysql:mysql /var/run/mysqld

# Instalar Tomcat
ENV TOMCAT_VERSION=9.0.85
RUN curl -fsSL https://archive.apache.org/dist/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    | tar -xzC /opt && \
    mv /opt/apache-tomcat-${TOMCAT_VERSION} /opt/tomcat

ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH

# Copiar WAR
COPY target/*.war /opt/tomcat/webapps/ROOT.war

# Hacer que Tomcat use el puerto dinámico de Render
RUN sed -i 's/port="8080"/port="${PORT}"/' /opt/tomcat/conf/server.xml

# Configuración phpMyAdmin en /var/www/html/phpmyadmin
RUN ln -s /usr/share/phpmyadmin /var/www/html/phpmyadmin

# Supervisor config
RUN echo "[supervisord]\n\
nodaemon=true\n\
\n\
[program:mysql]\n\
command=/usr/sbin/mysqld\n\
priority=10\n\
\n\
[program:tomcat]\n\
command=/opt/tomcat/bin/catalina.sh run\n\
priority=20\n\
\n\
[program:apache]\n\
command=/usr/sbin/apachectl -D FOREGROUND\n\
priority=30\n\
" > /etc/supervisor/conf.d/supervisord.conf

EXPOSE 3306
EXPOSE 8080

CMD ["/usr/bin/supervisord"] 