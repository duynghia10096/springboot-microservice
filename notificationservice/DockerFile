# Stage 1: Build
FROM maven:3.9.5-eclipse-temurin-17 as builder

WORKDIR /app

# Copy pom.xml và download dependencies
COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline

# Copy toàn bộ source code
COPY . .

# Build ứng dụng
RUN mvn clean install -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy file JAR từ stage builder
COPY --from=builder /app/target/*.jar app.jar

# Expose port ứng dụng
EXPOSE 8083

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
