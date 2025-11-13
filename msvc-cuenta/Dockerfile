# ---- build ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY msvc-cuenta/pom.xml /app/msvc-cuenta/pom.xml
WORKDIR /app/msvc-cuenta
RUN mvn -q -DskipTests dependency:go-offline
COPY msvc-cuenta/src /app/msvc-cuenta/src
RUN mvn -q -DskipTests package
# ---- run ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/msvc-cuenta/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8085
ENTRYPOINT ["java","-jar","/app/app.jar"]
