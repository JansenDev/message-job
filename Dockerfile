FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD

COPY src /build/src
COPY pom.xml /build/

WORKDIR /build/
RUN mvn package -DskipTests

CMD ls

FROM openjdk:17-jdk-alpine3.14

WORKDIR /app
RUN ls

COPY --from=MAVEN_BUILD /build/target/message-job-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "message-job-0.0.1-SNAPSHOT.jar"]
