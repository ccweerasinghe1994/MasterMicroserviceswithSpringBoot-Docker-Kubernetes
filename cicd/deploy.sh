#!/bin/bash

# Build all microservices using Jib

# Build accounts service
cd ../accounts
./mvnw compile jib:dockerBuild

# Build cards service
cd ../cards
./mvnw compile jib:dockerBuild

# Build config server
cd ../configserver
./mvnw compile jib:dockerBuild

# Build Eureka server
cd ../eurekaserver
./mvnw compile jib:dockerBuild

# Build gateway server
cd ../gatewayserver
./mvnw compile jib:dockerBuild

# Build loans service
cd ../loans
./mvnw compile jib:dockerBuild

# Return to the original directory
cd ../cicd

echo "All microservices have been built successfully."
