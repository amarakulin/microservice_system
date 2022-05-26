FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-gateway.jar
ENTRYPOINT ["java","-jar","/service-gateway.jar"]