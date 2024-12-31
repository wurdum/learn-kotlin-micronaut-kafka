package org.wurdum.infra

import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer

abstract class AbstractIntegrationTest {
    companion object {
        val server: EmbeddedServer = SharedTestContainer.server
        val client: HttpClient = SharedTestContainer.client
    }

    inline fun <reified T> getBean(): T = SharedTestContainer.getBean()

    val serverUrl: String
        get() = server.url.toString()
}