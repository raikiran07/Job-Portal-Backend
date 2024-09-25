#FROM openjdk:8-jdk-alpine
#FROM openjdk:17-alpine
FROM docker.repository.esi.adp.com/clientcentral/java:17.0.1-jdk


COPY target .

# CMD ["java","-jar","app.jar"]
ENTRYPOINT ["java","-jar","E-letter-0.0.1-SNAPSHOT.jar"]