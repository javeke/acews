FROM openjdk:8-jdk-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} acews.jar

ENTRYPOINT ["java", "-jar", "acews.jar"]