FROM maven:3.8.4-openjdk-17 AS BUILDER
WORKDIR /app
COPY pom.xml ./
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=BUILDER /app/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar"]