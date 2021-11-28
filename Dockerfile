FROM maven:3.8.4-openjdk-17 AS build
COPY src /src
COPY pom.xml .
RUN mvn clean package

FROM openjdk:15
COPY --from=build target/Currency-0.0.1.jar Currency-0.0.1.jar
ENTRYPOINT ["java","-jar","Currency-0.0.1.jar"]