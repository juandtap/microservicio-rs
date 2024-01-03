# Usa una imagen base con un servidor WildFly y Java
FROM jboss/wildfly:latest

# Copia el archivo WAR de tu aplicación al directorio de despliegue de WildFly
COPY target/microservicios-rs.war /opt/jboss/wildfly/standalone/deployments/

# Expone el puerto HTTP predeterminado de WildFly
EXPOSE 8080

# Comando para iniciar WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
