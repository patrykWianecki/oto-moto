FROM openjdk:15-jdk-oracle
EXPOSE 8080
ADD target/otomoto-service.jar otomoto-service.jar
ENTRYPOINT ["java", "-jar", "otomoto-service.jar"]