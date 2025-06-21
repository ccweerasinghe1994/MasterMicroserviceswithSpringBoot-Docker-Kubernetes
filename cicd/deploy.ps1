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

cd ../message
./mvnw.cmd compile jib:build

# Return to the original directory
cd ../cicd

Write-Output "All microservices have been built successfully."

# let's pull the latest changes

# create a new variable to store the latest changes
"accounts:s13", "cards:s13", "configserver:s13", "eurekaserver:s13", "gatewayserver:s13", "loans:s13","message:s13" | ForEach-Object {
    docker image pull magises/$_
}



