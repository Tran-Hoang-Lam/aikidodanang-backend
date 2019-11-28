FROM openjdk:8-jdk
COPY build/libs/aikidodanang-front-site-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.data.mongodb.uri=mongodb://admin:jYhjLYpqCCtMUH37cStz8rLxBXVLZ5h3qzmD9FvAk42ysrcYcQ@ds117469.mlab.com:17469/aikido-local", "--security.default-user=admin", "--security.default-password=Abc123#@!"]
