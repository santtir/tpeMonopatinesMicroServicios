# -------- build --------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY msvc-monopatin/pom.xml /app/msvc-monopatin/pom.xml
WORKDIR /app/msvc-monopatin
RUN mvn -q -DskipTests dependency:go-offline
COPY msvc-monopatin/src /app/msvc-monopatin/src
RUN mvn -q -DskipTests package

# -------- run --------
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/msvc-monopatin/target/*.jar app.jar
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/app.jar"]
