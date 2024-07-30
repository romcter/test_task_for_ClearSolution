## Use the official Gradle image to build the app
#FROM gradle:7.6.0-jdk17 AS build
#
## Set the working directory
#WORKDIR /test_task_for_ClearSolution
#
## Copy the Gradle wrapper and project files
#COPY gradle ./gradle
#COPY gradlew .
#COPY build.gradle .
#COPY settings.gradle .
#
## Download dependencies
##RUN ./gradlew build --no-daemon -x test
#
## Copy the application source code
#COPY src ./src
#
## Build the application
#RUN ./gradlew build
##
FROM amazoncorretto:17-alpine-jdk
ENV ARTIFACT_NAME=test_task_for_ClearSolution-0.0.1-SNAPSHOT.jar
ENV TEMP_BUILD_IMAGE=/build/libs/test_task_for_ClearSolution-0.0.1-SNAPSHOT.jar

COPY $TEMP_BUILD_IMAGE $ARTIFACT_NAME
EXPOSE 8081
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}