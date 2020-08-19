FROM openjdk:8-jdk-alpine

RUN apk update && apk upgrade

RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /api

USER spring:spring

COPY --chown=spring:spring @project.build.finalName@.jar bin/

COPY --chown=spring:spring *.yml config/

ENTRYPOINT java -jar /api/bin/@project.build.finalName@.jar