package org.wurdum

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.wurdum.infra.AbstractIntegrationTest

class WeatherControllerTest : AbstractIntegrationTest() {

    @Test
    fun weatherReport_always_becomesAvailableIn10s() {
        for (i in 1..10) {
            kotlin.runCatching {
                client.toBlocking().retrieve("/weather/Location-1")
            }.onSuccess {
                return
            }.onFailure {
                Thread.sleep(1000)
            }
        }

        fail("Weather report is not available")
    }
}