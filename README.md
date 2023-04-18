# Spring Boot Microservices with Docker MySQL instance

This is a sample project to demonstrate how to create a Spring Boot Microservice with a MySQL database instance running in a Docker container.

## Prerequisites
1. clone the project and run the following command to run the MySQL instance in a Docker container inside Docker directory 

    `docker-compose up -d`
2. Run the Spring Boot **Inventory** application in the IDE or using the following command

    `mvn spring-boot:run`
3. Run the Spring Boot **Shopping** application in the IDE or using the following command

    `mvn spring-boot:run`

## Testing the application
1. Create a new product using the [InventoryAPI](Inventory/InventoryAPI.http) commands
2. Browse the [ShoppingAPI](Shopping/Shopping.http) commands to test the application

    