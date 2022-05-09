FROM ubuntu
RUN apt-get update
RUN apt install openjdk-11-jre-headless -y
RUN mkdir /opt/tomcat
RUN chmod 777 /opt/tomcat
WORKDIR /opt/tomcat
ADD https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.62/bin/apache-tomcat-9.0.62.tar.gz .
RUN tar -zxvf apache-tomcat-9.0.62.tar.gz
RUN mv apache-tomcat-9.0.62/* /opt/tomcat
EXPOSE 8000
CMD ["/opt/tomcat/bin/catalina.sh" , "run"]
