# SteelCodeBot

SteelCodeBot is a Java application built with **Spring Boot** and deployed using Docker containers. This project includes a MySQL database as its backend, configured with `.env` files and `docker-compose`.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
    - [`.env` File](#env-file)
    - [Application Profiles](#application-profiles)
- [Usage Instructions](#usage-instructions)
    - [Building Docker Images](#building-docker-images)
    - [Running the Project](#running-the-project)
- [File Structure](#file-structure)
- [Common Issues](#common-issues)
- [Credits](#credits)

---

## Prerequisites

Before starting, make sure you have the following installed:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- JDK 21
- Gradle 8.13
- An active internet connection to download the necessary dependencies and containers.

### Recommended Versions

- Docker >= 20.10.0
- Docker Compose >= 1.29.0

---

## Configuration

### `.env` File

The `.env` file contains environment variables that configure the application and database. Adjust the development and production settings as required.

#### Example `.env`

```plaintext
# Development configuration
SPRING_PROFILE=dev

MYSQL_ROOT_PWD=devpass
MYSQL_DB=devdb
MYSQL_USER=devuser
MYSQL_PWD=devpwd

MYSQL_PORT=3306
DOCKER_MYSQL_PORT_MAPPING=1312:3306
DOCKER_SPRING_PORT_MAPPING=1337:8080
```

### Application Profiles

The application supports multiple Spring profiles (e.g., `dev`, `prod`).

- The active profile is set via the `SPRING_PROFILES_ACTIVE` environment variable.
- Ensure the correct properties file is used (`application-{profile}.properties`) for each environment.

---

## Usage Instructions

### Building Docker Images

To build the Docker image for the application, run:

```bash
docker build -t steelcodebot .
```

### Running the Project

1. Start the containers using `docker-compose`:
   ```bash
   docker-compose up --build
   ```

   This will start:
    - **bot-mysql**: A MySQL container with credentials defined in the `.env` file.
    - **bot-app**: A Spring Boot container configured to connect to the MySQL database.

2. Access the application on the configured port:
    - Localhost port for the application is defined in the `.env` file with `DOCKER_SPRING_PORT_MAPPING`. Default: `http://localhost:1337`.

### Stopping the Containers

To stop and remove containers and their associated volumes, run:
```bash
docker-compose down -v
```

---

## File Structure

- **`Dockerfile`**
  Defines the steps for building the Docker image for the application.

- **`docker-compose.yml`**
  Configures and orchestrates the MySQL backend and the Spring Boot application.

- **`application-prod.properties`**
  Spring configuration file for the production profile.

- **`.env`**
  Environment variables for MySQL and the Spring application.

- **`src/`**
  Contains the application source code.

---

## Credits

This project is developed with:
- **Spring Boot**
- **MySQL**
- **Docker**

A starting template for flexible deployments across multiple environments.