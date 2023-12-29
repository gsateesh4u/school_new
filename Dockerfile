# Docker Build Maven Stage
FROM maven:3.8.3-openjdk-17 AS build
# Copy folder in docker
WORKDIR /opt/app
COPY ./ /opt/app
RUN mvn clean install -DskipTests
# Run spring boot in Docker
FROM maven:3.8.3-openjdk-17

VOLUME /tmp
EXPOSE 8080
ARG APP_NAME="school"
ARG APP_VERSION="0.0.1"
ARG JAR_FILE="target/${APP_NAME}-${APP_VERSION}-SNAPSHOT.jar"
ADD ${JAR_FILE} app.jar
ENV SPRING_PROFILES_ACTIVE=dev
ENTRYPOINT ["java","-jar","/app.jar"]