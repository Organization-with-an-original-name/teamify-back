FROM eclipse-temurin:17.0.7_7-jre-alpine

COPY target ./

EXPOSE 8080

CMD ["java", "-jar", "teamify-0.0.1-SNAPSHOT.jar"]
