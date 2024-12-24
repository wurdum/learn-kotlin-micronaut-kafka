package org.wurdum

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic
import org.apache.kafka.clients.producer.RecordMetadata

@KafkaClient
interface WeatherChangeClient {

    @Topic("weather-changes")
    fun sendWeatherChange(@KafkaKey id: Long, weatherChange: WeatherChange): RecordMetadata
}