# ---------- STAGE 1: build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos POM padre y POM del módulo (el contexto es la RAÍZ)
COPY pom.xml /app/pom.xml
COPY msvc-viaje/pom.xml /app/msvc-viaje/pom.xml

# Pre-calentamos dependencias del módulo (ya con el parent visible)
WORKDIR /app/msvc-viaje
RUN mvn -q -DskipTests dependency:go-offline

# Copiamos el código del módulo y compilamos
COPY msvc-viaje/src /app/msvc-viaje/src
RUN mvn -q -DskipTests package

# ---------- STAGE 2: runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/msvc-viaje/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
