FROM openjdk:8-jdk
COPY build/libs/aikidodanang-0.0.1.jar /app.jar
EXPOSE 8080/tcp
ENV springProfile=dev
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=${springProfile}" , "/app.jar"]
