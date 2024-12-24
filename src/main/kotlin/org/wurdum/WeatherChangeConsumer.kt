package org.wurdum

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.Topic
import jakarta.inject.Singleton

@Singleton
@KafkaListener(groupId = "weather-change-consumer")
class WeatherChangeConsumer(private val repository: WeatherReportRepository) {

    @Topic("weather-changes")
    fun receive(weatherChange: WeatherChange) {
        val weatherReport = WeatherReport(
            location = weatherChange.location,
            condition = weatherChange.newCondition,
            temperature = weatherChange.temperature,
            timestamp = weatherChange.timestamp
        )
        repository.save(weatherReport)
    }
}
