# Project Overview

## Table of Contents

* [Main Goal](#main-goal)
* [About the Project](#about-the-project)
* [Technologies Used](#technologies-used)
* [Documentation](#documentation)
* [Latest Updates](#latest-updates)
* [Author](#author)

---

## About this Project

This project is an **E-commerce API**, which provides the following features:

* **Create a new user** and save it in the MySQL database

  > [Domain: Account](./src/main/java/com/marcos/ecommerce/account/)

* **Create a new product**

  > [Domain: Product](./src/main/java/com/marcos/ecommerce/product/)

* **Add items to the shopping cart**

  > [Domain: Cart](./src/main/java/com/marcos/ecommerce/cart/)

* **User authentication with JWT** (login and session management)

  > [Domain: Auth](./src/main/java/com/marcos/ecommerce/auth/)

* **Product search functionality**

  > [Domain: Search](./src/main/java/com/marcos/ecommerce/search/)

* **Static storefront frontend**

  > [Frontend files](./src/main/resources/static/)

* **Automated tests and CI workflow**

  > [Gradle tests](./src/test/java/com/marcos/ecommerce/)
  > [GitHub Actions workflow](./.github/workflows/ci.yml)

---

## Main Goal

This project was developed with two main purposes:

1. To consolidate my knowledge and practice with **Spring Boot** and **JPA/Hibernate** in Java.
2. To improve my proficiency by building a complete and functional application.

By the end of this project, I achieved a solid understanding of these technologies and how they integrate in real-world applications.

---

## About the Project

This project has a relatively simple structure. I divided it into **domains**, where each domain is responsible for specific business logic and functionality.

> The long-term idea is to evolve the project into a **microservices architecture**, decoupling the components so they can run independently.

> In the future, I also plan to add new features, such as containerizing the entire application with Docker for easier deployment.

---

## Technologies Used

The core technology of this project is **Spring Boot**, but several other tools and frameworks were used:

* **JWT** -> For managing user authentication and protecting routes.
* **MySQL** -> Database used to store user data, account information, and products.
* **Insomnia** -> Used for testing API endpoints and injecting data into the database.
* **Docker** -> The database runs inside a container for better isolation and resource management.
* **JPA/Hibernate** -> ORM for handling communication between the application and the database.
* **Shell Scripts (`.sh`)** -> Simple scripts created to populate the database with test data.
* **MockMvc/JUnit/Mockito** -> Automated route and service tests.
* **GitHub Actions** -> Continuous integration with tests, build, MySQL service, API startup, and smoke test.
* **HTML/CSS/JavaScript** -> Static frontend served by Spring Boot.

---

## Documentation

You can find more details about the project and how to run it in the following documents:

* [How to Run the Project](./docs/HowRunProject.md)
* [Database Configuration](./docs/DatabaseConfiguration.md)
* [Testing and Validation](./docs/HowTheTestsWereDone.md)
* [Frontend Storefront](./docs/FrontendStorefront.md)
* [Automated Tests](./docs/AutomatedTests.md)
* [Continuous Integration](./docs/ContinuousIntegration.md)

---

## Latest Updates

The project now includes a simple ecommerce frontend, automated tests, and a CI workflow.

* The frontend is served directly by Spring Boot from `src/main/resources/static`.
* The tests live in the standard Gradle test folder: `src/test/java`.
* The GitHub Actions workflow runs tests, builds the application, starts the API, and checks the public storefront.
* Static frontend routes were released in the security configuration so the application can open before authentication.
* Product and user delete routes were adjusted to bind the `{id}` path variable explicitly.

---

## Author

* **Name:** Marcos J. Ferreira
* **Email:** [marcos.j.lemes.2004@gmail.com](mailto:marcos.j.lemes.2004@gmail.com)

---
