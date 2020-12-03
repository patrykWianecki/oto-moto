FROM openjdk:15-oracle
EXPOSE 8085
ADD target/api-gateway.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]