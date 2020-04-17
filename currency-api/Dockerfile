FROM openjdk:11-jdk-oracle
EXPOSE 8082
ADD target/currency-api.jar currency-api.jar
ENTRYPOINT ["java", "-jar", "currency-api.jar"]