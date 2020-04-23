FROM openjdk:14-jdk-oracle
EXPOSE 8081
ADD target/otomoto-client.jar otomoto-client.jar
ENTRYPOINT ["java", "-jar", "otomoto-client.jar"]