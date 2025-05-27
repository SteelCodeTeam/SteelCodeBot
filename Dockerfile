FROM gradle:8.13-jdk21 AS build

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle ./gradle

COPY src ./src

RUN gradle build -x test

FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/build/libs/SteelCodeBot-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 1337
ENTRYPOINT ["java", "-jar", "app.jar"]
