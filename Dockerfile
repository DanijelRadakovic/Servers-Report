FROM maven:3.6.3-ibmjava-8-alpine AS appServer

LABEL maintainer="danijelradakovic@uns.ac.rs"

WORKDIR /usr/src/report
COPY . .
RUN ["mvn", "package", "-DskipTests"]


FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY --from=appServer /usr/src/report/target/report.jar ./

EXPOSE 8080

CMD ["java", "-jar", "report.jar"]
