# demo1

Spring Boot backend + React/Vite frontend built through a Maven reactor.

## CI/CD: Azure App Service deploy

Workflow file: `.github/workflows/azure-deploy.yml`

### What the workflow does

1. Runs `./mvnw clean package` from repo root.
2. This builds `frontend` first, copies `frontend/dist` into backend static resources, and then builds `backend`.
3. Uploads the backend JAR from `backend/target`.
4. Deploys the JAR to Azure App Service.

### Required GitHub Secrets

- `AZURE_WEBAPP_NAME`
- `AZURE_WEBAPP_PUBLISH_PROFILE`

### Trigger

- Push to `main`
- Manual run (`workflow_dispatch`)

