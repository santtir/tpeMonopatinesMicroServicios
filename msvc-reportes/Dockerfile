# ---- build ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY msvc-reportes/pom.xml /app/msvc-reportes/pom.xml
WORKDIR /app/msvc-reportes
RUN mvn -q -DskipTests dependency:go-offline
COPY msvc-reportes/src /app/msvc-reportes/src
RUN mvn -q -DskipTests package
# ---- run ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/msvc-reportes/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app/app.jar"]
