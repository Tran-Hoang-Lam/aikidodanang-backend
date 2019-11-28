FROM openjdk:8-jdk
COPY build/libs/aikidodanang-front-site-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=200m", "/app.jar"]
