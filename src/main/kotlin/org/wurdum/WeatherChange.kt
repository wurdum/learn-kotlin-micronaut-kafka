package org.wurdum

import io.micronaut.serde.annotation.Serdeable
import java.time.Instant

@Serdeable
data class WeatherChange(
    val location: String,
    val oldCondition: String,
    val newCondition: String,
    val timestamp: Instant
)
