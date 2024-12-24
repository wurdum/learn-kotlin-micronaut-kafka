package org.wurdum

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.Topic
import io.micronaut.scheduling.annotation.Scheduled
import jakarta.inject.Singleton
import org.apache.kafka.clients.producer.RecordMetadata
import java.time.Instant
import kotlin.random.Random

@Singleton
class WeatherChangeProducer(private val weatherChangeClient: WeatherChangeClient) {

    private val conditions = listOf("Sunny", "Cloudy", "Rainy", "Windy", "Snowy")

    @Scheduled(fixedDelay = "1s")
    fun produceWeatherChange() {
        val location = "Location-${Random.nextInt(1, 100)}"
        val oldCondition = conditions.random()
        val newCondition = conditions.random()
        val timestamp = Instant.now()

        val weatherChange = WeatherChange(location, oldCondition, newCondition, timestamp)
        weatherChangeClient.sendWeatherChange(weatherChange)
    }
}

@KafkaClient
interface WeatherChangeClient {

    @Topic("weather-changes")
    fun sendWeatherChange(weatherChange: WeatherChange): RecordMetadata
}
