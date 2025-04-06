FROM openjdk:17
WORKDIR /app

COPY target/*.jar /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

ENTRYPOINT ["java", "-jar", "api.jar"]
EXPOSE 8080
