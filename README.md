# Learn Kotlin Micronaut Kafka

This project demonstrates the integration of Micronaut with Kafka using Kotlin. It showcases how to set up a Kafka producer and consumer within a Micronaut application, leveraging Avro for message serialization.

## Key Features

- **Micronaut Framework**: Utilizes Micronaut for building a lightweight and efficient microservice.
- **Kafka Integration**: Implements a Kafka producer and consumer to handle message streaming.
- **Avro Serialization**: Uses Avro schemas for efficient and compact serialization of messages.

## Getting Started

### Prerequisites

- Docker and Docker Compose installed on your machine.
- Java Development Kit 21 (JDK) installed.

### Running the Kafka and Schema Registry

1. **Start Docker Compose**: Navigate to the project directory and run the following command to start Kafka, Schema Registry, and Kafka UI:

   ```bash
   docker-compose up -d
   ```

   This command will start the necessary services in the background.

2. **Verify Services**: You can verify that the services are running by accessing the Kafka UI at `http://localhost:8080`.

### Running the Application

1. **Build the Application**: Use Gradle to build the project. Run the following command in the project directory:

   ```bash
   ./gradlew build
   ```

2. **Run the Application**: Start the Micronaut application using the following command:

   ```bash
   ./gradlew run
   ```
