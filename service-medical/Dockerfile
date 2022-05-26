FROM openjdk:17
ARG JAR_FILE=medical-server/target/*.jar
COPY ${JAR_FILE} service-medical.jar
ENTRYPOINT ["java","-jar","/service-medical.jar"]