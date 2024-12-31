package org.wurdum.infra

import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.apache.kafka.clients.admin.NewTopic
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.Network
import org.testcontainers.kafka.ConfluentKafkaContainer
import org.testcontainers.utility.DockerImageName
import java.util.concurrent.TimeUnit

object TestContainers {
    private val network = Network.newNetwork()

    private val kafka = ConfluentKafkaContainer(DockerImageName
        .parse("confluentinc/cp-kafka:7.8.0"))
        .withNetwork(network)
        .apply { start() }

    private val schemaRegistry = SchemaRegistryContainer("confluentinc/cp-schema-registry:7.8.0")
        .withNetwork(network)
        .withKafka(kafka)
        .apply { start() }

    private val adminClient by lazy {
        AdminClient.create(mapOf(
            AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers
        ))
    }

    val bootstrapServers: String get() = kafka.bootstrapServers
    val schemaRegistryUrl: String get() = schemaRegistry.schemaRegistryUrl

    init {
        adminClient.createTopics(listOf(NewTopic("weather-changes", 1, 1))).all().get(10, TimeUnit.SECONDS)
    }

    fun stop() {
        schemaRegistry.stop()
        kafka.stop()
        network.close()
    }
}

class SchemaRegistryContainer(dockerImageName: String) : GenericContainer<SchemaRegistryContainer>(dockerImageName) {
    fun withKafka(kafka: ConfluentKafkaContainer): SchemaRegistryContainer = apply {
        withExposedPorts(8081)
        withEnv("SCHEMA_REGISTRY_HOST_NAME", "schema-registry")
        withEnv("SCHEMA_REGISTRY_KAFKASTORE_BOOTSTRAP_SERVERS", "${kafka.networkAliases[0]}:9092")
        withEnv("SCHEMA_REGISTRY_LISTENERS", "http://0.0.0.0:8081")
    }

    val schemaRegistryUrl: String
        get() = "http://$host:${getMappedPort(8081)}"
}