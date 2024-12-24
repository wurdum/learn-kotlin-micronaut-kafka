package org.wurdum

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable


@Controller("/weather")
class WeatherController(private val repository: WeatherReportRepository) {

    @Get("/{location}")
    fun get(@PathVariable location: String): HttpResponse<WeatherReport> {
        val existingReport = repository.findByLocation(location)
        return existingReport.let { HttpResponse.ok(existingReport) } ?: HttpResponse.notFound()
    }
}
