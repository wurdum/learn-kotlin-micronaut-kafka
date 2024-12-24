package org.wurdum

import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import java.time.Instant
import kotlin.random.Random

@Singleton
class WeatherChangeProducer(private val weatherChangeClient: WeatherChangeClient) {

    private val conditions = listOf("Sunny", "Cloudy", "Rainy", "Windy", "Snowy")

    @Scheduled(fixedDelay = "1s")
    fun produceWeatherChange() {
        val location = "Location-${Random.nextInt(1, 5)}"
        val condition = conditions.random()
        val temperature = Random.nextInt(-20, 40)
        val timestamp = Instant.now()

        val weatherChange = WeatherChange(location, condition, temperature, timestamp)
        weatherChangeClient.sendWeatherChange(Instant.now().toEpochMilli(), weatherChange)
    }
}