# API Testing

To test the API, I used **Insomnia**, a tool that allows sending HTTP requests to API endpoints.

In addition, I also used **cURL scripts** (`.sh` files) to quickly populate the database and perform automated test requests.

The project now also includes automated Gradle tests with JUnit, Mockito, and MockMvc.

> See the automated test documentation: [Automated Tests](./AutomatedTests.md)

---

## Tools and Versions

```bash
/...:~$ insomnia --version
13:20:14.729 › Running version 11.5.0
```

---

## Insomnia Collection

[Insomnia Tests and Routes](../test/insomnia-collection.yaml)

> This file contains pre-configured API requests and example test cases and Collection;

---

## API Routes

[API Routes JSON](../test/api-routes.json)

> All available API routes are mapped in this JSON file for reference.

---

## Data Injection Scripts

The following scripts were used to populate the database with test data:

* [Add Users](../test/SetUser.sh)
* [Add Products](../test/setProduct.sh)

---

## Test Evidence

[API Test Screenshot](./Captura%20de%20tela%202025-09-01%20104634.png)

---

With these resources, you can reproduce the tests locally or adapt them to your own environment.

---

## Automated Test Suite

Automated tests were added under:

```bash
src/test/java/com/marcos/ecommerce/
```

They cover:

* Main API route contracts with `MockMvc`
* Service layer behavior with Mockito
* User, authentication, product, search, and cart flows

Run them with:

```bash
./gradlew test
```

On Windows:

```bash
.\gradlew.bat test
```

