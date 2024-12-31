package org.wurdum.infra

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer

object SharedTestContainer {
    val server: EmbeddedServer = ApplicationContext.run(
        EmbeddedServer::class.java, mapOf(
            "micronaut.application.name" to "learn-kotlin-micronaut-kafka",
            "micronaut.server.port" to -1,
            "kafka.bootstrap.servers" to TestContainers.bootstrapServers,
            "kafka.producers.weather-changes.schema.registry.url" to TestContainers.schemaRegistryUrl,
            "kafka.consumers.weather-changes.schema.registry.url" to TestContainers.schemaRegistryUrl,
        )
    )

    val client: HttpClient = server.applicationContext.createBean(
        HttpClient::class.java,
        server.url
    )

    inline fun <reified T> getBean(): T = server.applicationContext.getBean(T::class.java)

    init {
        Runtime.getRuntime().addShutdownHook(Thread {
            client.close()
            server.close()
            TestContainers.stop()
        })
    }
}