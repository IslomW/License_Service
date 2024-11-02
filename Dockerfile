FROM maven:3.8.6-amazoncorretto-17 AS bulid
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:go-offline
COPY src /build/src/
RUN mvn package -DskipTest

#Run stage
FROM openjdk:17-alpine

ARG JAR_FILE=/build/target/*.jar
COPY --from=bulid $JAR_FILE /opt/docker-test/app.jar
ENTRYPOINT ["java", "-jar", "/opt/docker-test/app.jar"]