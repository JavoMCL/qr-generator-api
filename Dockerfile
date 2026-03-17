FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/qr_generator_api-0.0.1-SNAPSHOT.jar
COPY $JAR_FILE qr_generator_api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "qr_generator_api.jar"]