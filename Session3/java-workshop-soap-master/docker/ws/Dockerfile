FROM tomcat:8.5-jre8

RUN echo "export JAVA_OPTS=\"-Dapp.env=soap-demo\"" > /usr/local/tomcat/bin/setenv.sh
COPY ./soap-demo.war /usr/local/tomcat/webapps/soap-demo.war

CMD ["catalina.sh", "run"]