FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/user-service-*.jar user-service.jar

ENTRYPOINT ["java","-jar","/app/user-service.jar"]
