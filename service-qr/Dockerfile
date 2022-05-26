FROM openjdk:17
ARG JAR_FILE=qr-server/target/*.jar
COPY ${JAR_FILE} service-qr.jar
ENTRYPOINT ["java","-jar","/service-qr.jar"]