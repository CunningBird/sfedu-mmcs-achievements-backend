FROM bellsoft/liberica-openjdk-alpine:17.0.4.1 AS build

ARG NEXUS_USERNAME
ENV NEXUS_USERNAME $NEXUS_USERNAME

ARG NEXUS_PASSWORD
ENV NEXUS_PASSWORD $NEXUS_PASSWORD

COPY --chown=gradle:gradle . /app
WORKDIR /app

RUN chmod +x gradlew && ./gradlew build --no-daemon

FROM bellsoft/liberica-openjre-alpine:17.0.4.1

RUN mkdir "app" && adduser -D application && chown -R application /app
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

USER application
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]