FROM openjdk:17
ADD /target/finalFile.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]