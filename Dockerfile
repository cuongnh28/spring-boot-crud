FROM openjdk:8
LABEL authors="cuongnh"

COPY out/artifacts/transport_management_jar/transport-management.jar app.jar

CMD java -jar app.jar