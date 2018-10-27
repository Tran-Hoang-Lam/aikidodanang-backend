FROM openjdk:8-jdk
COPY build/libs/aikidodanang-front-site-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080/tcp
ENV springProfile=dev
ENTRYPOINT ["java", "-jar", "/app.jar"]
