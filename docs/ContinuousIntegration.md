# Continuous Integration

The project now includes a GitHub Actions workflow for automated validation.

---

## Workflow Location

```bash
.github/workflows/ci.yml
```

Workflow name:

```bash
Java CI
```

---

## When It Runs

The workflow runs on:

* Pushes to `main` or `master`
* Pull requests targeting `main` or `master`

---

## What It Does

The workflow executes the following steps:

1. Checks out the repository.
2. Installs Java 17 using Temurin.
3. Enables Gradle caching.
4. Starts a MySQL 8 service.
5. Runs the test suite.
6. Builds the Spring Boot jar.
7. Starts the API from the generated jar.
8. Waits until the API responds on port `8080`.
9. Runs a smoke test against the public storefront.
10. Stops the API process.

---

## MySQL Service

The CI MySQL container uses the same default credentials configured in `application.properties`:

```bash
Database: system-01
User: marcos
Password: senha123
Port: 3306
```

This allows the application to start in the workflow without requiring an external database.

---

## Smoke Test

After building the jar, the workflow starts the API and checks:

```bash
http://127.0.0.1:8080/
```

The smoke test confirms that the Spring Boot application starts and serves the frontend page.

---

## Best Practices Included

* Java version is pinned to 17.
* Gradle cache is enabled for faster builds.
* The workflow has read-only repository permissions.
* MySQL runs as an isolated CI service.
* The API process is stopped even if a later step fails.
* Tests run before the jar build.
