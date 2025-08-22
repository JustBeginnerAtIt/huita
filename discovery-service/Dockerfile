FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/discovery-service-*.jar discovery-service.jar

ENTRYPOINT ["java","-jar","/app/discovery-service.jar"]
