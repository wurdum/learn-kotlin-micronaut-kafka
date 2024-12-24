package org.wurdum

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.serde.annotation.Serdeable
import java.time.Instant


@Controller("/weather")
class WeatherController {

    @Get("/{location}")
    fun get(@PathVariable location: String): HttpResponse<WeatherReport> {
        val weatherReport = WeatherReport(location, "Sunny", 25, Instant.now())
        return HttpResponse.ok(weatherReport)
    }
}

@Serdeable
data class WeatherReport(val location: String, val condition: String, val temperature: Int, val timestamp: Instant)
