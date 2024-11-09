
FROM openjdk:18-jdk-alpine

WORKDIR /app


COPY ./base.jar /app/base.jar


COPY ./lib /app/lib/

CMD ["java", "-jar", "database-tool.jar"]

