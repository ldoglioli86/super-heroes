FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/superheroes-api-0.0.1-SNAPSHOT.jar superheroes-api.jar
ENTRYPOINT ["java", "-jar", "/superheroes-api.jar"]
