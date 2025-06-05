FROM gradle:jdk21-graal AS build
WORKDIR /usr/app/

COPY settings.gradle build.gradle ./
COPY src ./src

RUN ./gradlew build --no-daemon --stacktrace

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /usr/app/build/libs/globalsolution-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]