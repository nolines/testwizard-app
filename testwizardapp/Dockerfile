FROM maven:3.6.3 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn clean package -DskipTests
FROM eclipse-temurin:17.0.4.1_1-jre-jammy

ARG JAR_FILE=/usr/src/app/target/*.jar

COPY --from=maven ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]