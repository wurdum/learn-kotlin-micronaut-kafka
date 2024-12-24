package org.wurdum

import jakarta.inject.Inject
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.serde.annotation.Serdeable
import java.time.Instant


@Controller("/weather")
class WeatherController {

    @Inject
    lateinit var weatherReportRepository: WeatherReportRepository

    @Get("/{location}")
    fun get(@PathVariable location: String): HttpResponse<WeatherReport> {
        val existingReport = weatherReportRepository.findByLocation(location)
        val weatherReport = existingReport ?: WeatherReport(location, "Sunny", 25, Instant.now())
        weatherReportRepository.save(weatherReport)
        return HttpResponse.ok(weatherReport)
    }
}

@Serdeable
data class WeatherReport(val location: String, val condition: String, val temperature: Int, val timestamp: Instant)
