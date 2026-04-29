# Frontend Storefront

The project now includes a static ecommerce frontend served by Spring Boot.

---

## Location

The frontend files are located in:

```bash
src/main/resources/static/
```

Main files:

* `index.html` -> page structure
* `styles.css` -> visual style and responsive layout
* `app.js` -> API integration and UI behavior

Spring Boot automatically serves these files when the application starts.

---

## How to Access

After running the API, open:

```bash
http://localhost:8080/
```

The frontend runs on the same host and port as the backend, which avoids CORS problems during local development.

---

## Available Features

The storefront can communicate with the API and execute the main ecommerce flows:

* Create a user account
* Login and store the JWT token in `localStorage`
* Validate the current token
* Search products
* Create products
* Update products
* Delete products
* Add products to the cart
* Load the cart by user identifier

---

## API Routes Used by the Frontend

```bash
POST   /api/v1/users/newUsers
POST   /api/v1/auth/login
GET    /api/v1/auth/token
GET    /api/v1/search
POST   /api/v1/product/create
POST   /api/v1/product/update
DELETE /api/v1/product/delete/{id}
POST   /api/v1/cartShopping/add
GET    /api/v1/cartShopping/{userId}
```

Important note: the real cart route in the controller is `/api/v1/cartShopping/add`.
This is different from the old route reference that used `/adicionar`.

---

## Security Configuration

The static frontend files are public routes:

```bash
/
/index.html
/styles.css
/app.js
/favicon.ico
```

Protected API routes still require the `Authorization: Bearer <token>` header.

---

## Local Usage Flow

1. Start the MySQL database.
2. Start the Spring Boot API.
3. Open `http://localhost:8080/`.
4. Create an account or login.
5. Use the product and cart panels to test the API visually.
