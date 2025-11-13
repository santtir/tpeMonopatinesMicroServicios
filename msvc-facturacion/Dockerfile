# ---- build ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY msvc-facturacion/pom.xml /app/msvc-facturacion/pom.xml
WORKDIR /app/msvc-facturacion
RUN mvn -q -DskipTests dependency:go-offline
COPY msvc-facturacion/src /app/msvc-facturacion/src
RUN mvn -q -DskipTests package
# ---- run ----
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/msvc-facturacion/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8086
ENTRYPOINT ["java","-jar","/app/app.jar"]
