# Build all microservices using Jib

# Build accounts service
cd ../accounts
./mvnw.cmd compile jib:build

# Build cards service
cd ../cards
./mvnw.cmd compile jib:build

# Build config server
cd ../configserver
./mvnw.cmd compile jib:build

# Build Eureka server
cd ../eurekaserver
./mvnw.cmd compile jib:build

# Build gateway server
cd ../gatewayserver
./mvnw.cmd compile jib:build

# Build loans service
cd ../loans
./mvnw.cmd compile jib:build

# Return to the original directory
cd ../cicd

Write-Output "All microservices have been built successfully."

# Pull the latest changes for all microservices
cd ../accounts
# let's pull the latest changes

# create a new variable to store the latest changes
"accounts:s12", "cards:s12", "configserver:s12", "eurekaserver:s12", "gatewayserver:s12", "loans:s12" | ForEach-Object {
    docker image pull magises/$_
}



