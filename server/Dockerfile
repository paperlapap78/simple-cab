FROM java:8-jre-alpine

ENV APP_PATH=/app

RUN mkdir -p $APP_PATH
WORKDIR $APP_PATH

COPY target/server-1.0-SNAPSHOT.jar app.jar
COPY src/dist/config/simple-cab-docker.yml simple-cab.yml

EXPOSE 8080
EXPOSE 8081

CMD ["java", "-jar", "app.jar", "server", "simple-cab.yml"]