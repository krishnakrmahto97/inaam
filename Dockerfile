FROM openjdk:15-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/main-0.0.1-SNAPSHOT.jar main-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "main-0.0.1-SNAPSHOT.jar"]