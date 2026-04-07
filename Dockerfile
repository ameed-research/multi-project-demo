# ── Build stage ──────────────────────────────────────────────────────────────
FROM eclipse-temurin:25-jdk AS build

WORKDIR /workspace

# Copy Maven wrapper and root POM first for better layer caching
COPY mvnw ./
COPY .mvn/ .mvn/
COPY pom.xml ./

# Copy module POMs
COPY frontend/pom.xml frontend/
COPY backend/pom.xml backend/

# Download dependencies (cached unless POMs change)
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B --no-transfer-progress || true

# Copy source trees
COPY frontend/ frontend/
COPY backend/ backend/

# Build everything (frontend-maven-plugin downloads Node internally)
RUN ./mvnw clean package -DskipTests --batch-mode --no-transfer-progress

# ── Runtime stage ─────────────────────────────────────────────────────────────
FROM eclipse-temurin:25-jre AS runtime

WORKDIR /app

COPY --from=build /workspace/backend/target/backend-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
