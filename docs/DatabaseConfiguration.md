# Database Setup

I chose **MySQL** as the database since I have more proficiency working with it.

> In the future, I plan to integrate **AWS S3** to store product information more efficiently.

---

## Requirements

* **Docker** installed.

My Docker version:

```bash
/...:~$ docker --version
Docker version 28.3.3, build 980b856
```

* **Initialization script**

This script contains all the database configuration. You just need to run it.

[settings.sh](../db-mysql/settings.sh)

---

## How to run the script

```bash
# Navigate to the directory where the script is located and give it execution permission
chmod u+x settings.sh

# Run the script
./settings.sh
```

---

## Verifying the container

After running the script, make sure the container is up and running correctly:

```bash
# Command to display running containers and check if the database is active
/...:~$ docker ps

CONTAINER ID   IMAGE          COMMAND                  CREATED       STATUS       PORTS                                                    NAMES
a53b9264fb8c   mysql:8.0.30   "docker-entrypoint.s…"   13 days ago   Up 4 hours   0.0.0.0:3306->3306/tcp, [::]:3306->3306/tcp, 33060/tcp   dataBase-ecommerce
```

---

After completing these steps, the database will be ready and the API can be executed.

---

# JPA Configuration

> In this file, you can configure **JPA** to connect to the database, including credentials, URL, and other properties.

* [Application Properties](../src/main/resources/application.properties)

---

