FROM openjdk:11-jdk-oracle
EXPOSE 8083
ADD target/location-api.jar location-api.jar
ENTRYPOINT ["java", "-jar", "location-api.jar"]