FROM openjdk:17
ARG JAR_FILE=person-server/target/*.jar
COPY ${JAR_FILE} service-person.jar
ENTRYPOINT ["java","-jar","/service-person.jar"]