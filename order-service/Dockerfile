FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/order-service-*.jar order-service.jar

ENTRYPOINT ["java","-jar","/app/order-service.jar"]
