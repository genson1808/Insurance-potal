FROM openjdk:8-jre-alpine
EXPOSE 8080
WORKDIR /app

COPY target/Insurance_portal-0.0.1-SNAPSHOT.jar .

ENTRYPOINT [ "java", "-jar", "Insurance_portal-0.0.1-SNAPSHOT.jar" ]