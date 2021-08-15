FROM openjdk:8-jdk-alpine
COPY ./target/*.jar crm.jar
ENTRYPOINT ["java", "-jar", "crm.jar"]