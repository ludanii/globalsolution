FROM gradle:jdk21-graal AS build
WORKDIR /app
COPY . .
RUN gradle clean build -x test

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Porta exposta para a aplicação
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]