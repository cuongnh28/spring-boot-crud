FROM openjdk:8
LABEL authors="cuongnh"

COPY out/artifacts/spring_boot_crud_jar/spring-boot-crud.jar app.jar

CMD java -jar app.jar