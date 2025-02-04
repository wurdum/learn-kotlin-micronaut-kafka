package org.wurdum

import io.micronaut.serde.annotation.Serdeable
import java.time.Instant

@Serdeable
data class WeatherChange(
    val location: String,
    val condition: String,
    val temperature: Int,
    val timestamp: Instant
)