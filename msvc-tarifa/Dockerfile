# ---- build ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY msvc-tarifa/pom.xml /app/msvc-tarifa/pom.xml
WORKDIR /app/msvc-tarifa
RUN mvn -q -DskipTests dependency:go-offline
COPY msvc-tarifa/src /app/msvc-tarifa/src
RUN mvn -q -DskipTests package
# ---- run ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/msvc-tarifa/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app/app.jar"]
