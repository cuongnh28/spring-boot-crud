FROM openjdk:8

MAINTAINER cuongnh

LABEL authors="cuongnh"

COPY target/spring-boot-crud-0.0.1-SNAPSHOT.jar app.jar

CMD java -jar app.jar