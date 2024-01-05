# Usa una imagen base con un servidor WildFly y Java
FROM quay.io/wildfly/wildfly
ENV WILDFLY_USER user
ENV WILDFLY_PASS userPassword
RUN echo "Adding WildFly administrator"
RUN $JBOSS_HOME/bin/add-user.sh -u $WILDFLY_USER -p $WILDFLY_PASS --silent

# Copia el archivo WAR de tu aplicación al directorio de despliegue de WildFly
ADD target/microservicios-rs.war /opt/jboss/wildfly/standalone/deployments/

# Crea el directorio para el modulo PostgreSQL y copia los archivos
RUN mkdir -p /opt/jboss/wildfly/modules/org/postgresql/main/
COPY configfiles/module.xml /opt/jboss/wildfly/modules/org/postgresql/main/
COPY configfiles/postgresql-42.2.18.jar /opt/jboss/wildfly/modules/org/postgresql/main/

COPY configdata/standalone.xml /opt/jboss/wildfly/standalone/configuration/

# Expone el puerto HTTP predeterminado de WildFly
EXPOSE 8080
EXPOSE 8009
EXPOSE 9990
# Comando para iniciar WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
