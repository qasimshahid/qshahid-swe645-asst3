# Qasim Shahid
# Create a Docker container for our Spring Boot application that runs an API on port 8080.

FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the Maven target directory
COPY surveyapi/target/surveyapi-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]