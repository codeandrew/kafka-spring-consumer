FROM openjdk:8-alpine3.8
MAINTAINER Jean Andrew Fuentes <jeanandrewfuentes@gmail.com>

WORKDIR /usr/src
ADD build/libs/consumer-0.0.1-SNAPSHOT.jar /usr/src/docker-consumer.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "docker-consumer.jar"]
