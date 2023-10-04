FROM adoptopenjdk/openjdk8:x86_64-alpine-jre8u282-b08
ADD /target/*.jar /
EXPOSE 8080
EXPOSE 8081
CMD ["java", "-jar", "/rinha-backend-1.0.0-SNAPSHOT.jar"]
