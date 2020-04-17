FROM openjdk:11-jdk-oracle
EXPOSE 8084
ADD target/data-importer.jar data-importer.jar
ENTRYPOINT ["java", "-jar", "data-importer.jar"]