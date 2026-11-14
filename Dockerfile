FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
 
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn -B clean package
 
FROM eclipse-temurin:17-jre
WORKDIR /app
 
# Don't run as root inside the container
RUN useradd --system --uid 1001 spring
COPY --from=build /build/api-app/target/api-app-1.0.0.jar app.jar
USER spring
 
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
