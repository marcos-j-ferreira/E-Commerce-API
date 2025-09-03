# Running the Project

To run this project, follow the steps below:

---

## Requirements

* **Docker and Java** installed

My Docker and Java version :

```bash
/...:~$ docker --version
Docker version 28.3.3, build 980b856


# Java version;

#This is not necessary, as the project will be run on a different version of Java. 
#However, to ensure integrity and a margin of error of zero percent, 
#this was the version used for development and testing.

/...:$ java --version
openjdk 17.0.16 2025-07-15
OpenJDK Runtime Environment (build 17.0.16+8-Ubuntu-0ubuntu122.04.1)
OpenJDK 64-Bit Server VM (build 17.0.16+8-Ubuntu-0ubuntu122.04.1, mixed mode, sharing)

```

---

## Step 1: Configure the database

You first need to run the script that contains the database configuration:

[Database Configuration](./DatabaseConfiguration.md)

---

## Step 2: Run the application

After setting up the database, follow these steps:

```bash
# Clone the remote repository
git clone https://github.com/marcos-j-ferreira/E-Commerce-API.git

# Verify that the repository was cloned successfully
ls

# Make sure the database container is running (from the previous step)
docker ps

# Navigate into the project folder
cd E-Commerce-API

# Check that the 'gradlew' file exists in the current directory
ls

# Compile the project
./gradlew compileJava

# Or run the application directly (this also compiles automatically)
./gradlew bootRun
```

---

## Step 3: Check the logs

If everything works correctly, you should see the following log:

[Successful startup logs](../logs/logs.log)

> Otherwise, the application will fail to start and you will see error logs instead.
