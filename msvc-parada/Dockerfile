# ---- build ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY msvc-parada/pom.xml /app/msvc-parada/pom.xml
WORKDIR /app/msvc-parada
RUN mvn -q -DskipTests dependency:go-offline
COPY msvc-parada/src /app/msvc-parada/src
RUN mvn -q -DskipTests package
# ---- run ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/msvc-parada/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app/app.jar"]
