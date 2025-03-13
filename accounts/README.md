# Running the Project with Docker

This section provides instructions to build and run the project using Docker.

## Prerequisites

- Ensure Docker and Docker Compose are installed on your system.
- Docker version: 21-jdk-slim (runtime), 3.8.5-openjdk-21 (build).

## Environment Variables

- No specific environment variables are required for this setup. Uncomment and configure the `env_file` section in the `docker-compose.yml` file if needed.

## Build and Run Instructions

1. Build and start the services using Docker Compose:

   ```bash
   docker-compose up --build
   ```

2. Access the application at `http://localhost:8082`.

## Exposed Ports

- `8082`: Application service.

## Notes

- The application is built using Maven and runs on OpenJDK 21.
- The `accounts-0.0.1-SNAPSHOT.jar` file is generated during the build process and used in the runtime image.

For further details, refer to the Dockerfile and `docker-compose.yml` file included in the project.