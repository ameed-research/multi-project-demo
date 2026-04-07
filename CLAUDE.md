# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build entire project (frontend + backend into single JAR)
./mvnw clean package

# Build skipping tests (matches CI/CD behavior)
./mvnw clean package -DskipTests --batch-mode

# Run backend (after building)
./mvnw spring-boot:run -pl backend

# Run tests
./mvnw test

# Frontend development only (hot reload, no Maven)
cd frontend && npm install && npm run dev
```

## Architecture

This is a Maven multi-module monorepo producing a **single deployable JAR** with an embedded React frontend.

**Build pipeline:**
1. `frontend/` module runs `npm install` + `npm run build` via `frontend-maven-plugin`, producing `/frontend/dist`
2. `backend/` module copies `/frontend/dist` → `backend/src/main/resources/static`, then packages a Spring Boot fat JAR

**Modules:**
- `frontend/` — React 18 + Vite 5 SPA
- `backend/` — Spring Boot 4.0.5 (Java 25), REST API with MongoDB and Azure File Share integrations

**Deployment:** GitHub Actions (`.github/workflows/main_carpentry1.yml`) builds on push to `main` and deploys `backend/target/backend-*.jar` to Azure App Service.

## Backend Structure

```
backend/src/main/java/com/ameed/demo1/
├── Demo1Application.java           # Entry point, @EnableMongoRepositories
└── controllers/
    ├── FilesController.java        # /api/test/files/* — Azure File Share ops
    ├── TestConfig.java             # Azure client beans
    └── db/
        ├── DbController.java       # /api/test/db/* — MongoDB ops
        ├── MyMongoRepository.java  # Spring Data repository
        └── Person.java             # MongoDB document model
```

**API endpoints:**
- `GET /api/test/db/create` — Save a Person to MongoDB
- `GET /api/test/db/list` — List all Persons
- `GET /api/test/files/list` — List Azure File Share files
- `GET /api/test/files/create` — Create a test file
- `GET /api/test/files/get` — Download file content

## Required Environment Variables

The backend (`application.yaml`) reads these at runtime:

| Variable | Purpose |
|---|---|
| `MONGODB_CONNECTION_STRING` | MongoDB connection URI |
| `MONGODB_DATABASE_NAME` | MongoDB database name |
| `STORAGE_ACCOUNT_NAME` | Azure storage account |
| `STORAGE_ACCOUNT_KEY` | Azure storage key |

CI/CD also requires GitHub secrets `AZURE_WEBAPP_NAME` and `AZURE_WEBAPP_PUBLISH_PROFILE`.
