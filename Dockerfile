FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG APP_NAME="school"
ARG APP_VERSION="0.0.1"
ARG JAR_FILE="target/${APP_NAME}-${APP_VERSION}-SNAPSHOT.jar"
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]