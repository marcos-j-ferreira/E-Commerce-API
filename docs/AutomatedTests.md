# Automated Tests

The project now includes automated tests in the standard Gradle test folder.

---

## Location

```bash
src/test/java/com/marcos/ecommerce/
```

Current test files:

* `controller/ControllerRoutesTest.java`
* `service/ServiceLayerTest.java`

The root `test/` folder is still used for manual resources such as Insomnia collections, route JSON, and shell scripts.

---

## Controller Route Tests

`ControllerRoutesTest` validates the HTTP contract of the main API routes using `MockMvc`.

Covered areas:

* Account routes
* Authentication routes
* Product routes
* Search route
* Cart routes

These tests use mocked services, so they do not require MySQL.

---

## Service Tests

`ServiceLayerTest` validates the business behavior of the service layer using Mockito.

Covered areas:

* User creation with encoded password
* Duplicate email validation
* Login with valid credentials
* Invalid password handling
* Product creation
* Product deletion
* Cart creation and cart lookup
* Product search response mapping

These tests use mocked repositories, so they are fast and isolated.

---

## How to Run

Make sure Java 17 is installed and configured in `JAVA_HOME`.

On Linux/macOS:

```bash
./gradlew test
```

On Windows:

```bash
.\gradlew.bat test
```

---

## Important Notes

The tests are intentionally focused on routes and services without depending on the local MySQL database.

The CI workflow starts MySQL only for the final application startup and smoke test, because the real API needs the database to boot.
