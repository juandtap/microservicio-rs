# Usa una imagen base con un servidor WildFly y Java
FROM quay.io/wildfly/wildfly
ENV WILDFLY_USER user
ENV WILDFLY_PASS userPassword

ENV DS_NAME myDS
ENV DS_USER consultas
ENV DS_PASS QueryConSql.pwd
ENV DS_URI jdbc:postgresql://srvdb/consultas

ENV JBOSS_CLI $JBOSS_HOME/bin/jboss-cli.sh
ENV DEPLOYMENT_DIR $JBOSS_HOME/standalone/deployments/

RUN echo "Adding WildFly administrator"
RUN $JBOSS_HOME/bin/add-user.sh -u $WILDFLY_USER -p $WILDFLY_PASS --silent

# Configure Wildfly server
RUN echo "Starting WildFly server" && \
      bash -c '$JBOSS_HOME/bin/standalone.sh -c standalone.xml &' && \
      bash -c 'until `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null | grep -q running`; do echo `$JBOSS_CLI -c ":read-attribute(name=server-state)" 2> /dev/null`; sleep 1; done' && \
      curl --location --output /tmp/postgresql-42.2.18.jar --url https://jdbc.postgresql.org/download/postgresql-42.2.18.jar && \
      $JBOSS_CLI --connect --command="/extension=org.wildfly.extension.microprofile.openapi-smallrye:add" && \
      $JBOSS_CLI --connect --command="/subsystem=microprofile-openapi-smallrye:add" && \
      $JBOSS_CLI --connect --command="/subsystem=undertow/server=default-server/ajp-listener=ajp-listener:add(socket-binding=ajp, scheme=https, enabled=true)" && \
      $JBOSS_CLI --connect --command="/subsystem=undertow:write-attribute(name=statistics-enabled,value=true)" && \
      $JBOSS_CLI --connect --command="module add --name=org.postgres --resources=/tmp/postgresql-42.2.18.jar --dependencies=javax.api,javax.transaction.api" && \
      $JBOSS_CLI --connect --command="/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgres",driver-class-name=org.postgresql.Driver)" && \
      $JBOSS_CLI --connect --command="data-source add \
        --name=${DS_NAME} \
        --jndi-name=java:jboss/datasources/${DS_NAME} \
        --user-name=${DS_USER} \
        --password=${DS_PASS} \
        --driver-name=postgres \
        --connection-url=${DS_URI} \
        --min-pool-size=10 \
        --max-pool-size=20 \
        --blocking-timeout-wait-millis=5000 \
        --statistics-enabled=true \
        --enabled=true" && \
      $JBOSS_CLI --connect --command=":shutdown" && \
      rm -rf $JBOSS_HOME/standalone/configuration/standalone_xml_history/ $JBOSS_HOME/standalone/log/* && \
      rm -f /tmp/*.jar
# Copia el archivo WAR de tu aplicación al directorio de despliegue de WildFly
ADD target/microservicios-rs.war /opt/jboss/wildfly/standalone/deployments/

# Expone el puerto HTTP predeterminado de WildFly
EXPOSE 8080
EXPOSE 8009
EXPOSE 9990
# Comando para iniciar WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
