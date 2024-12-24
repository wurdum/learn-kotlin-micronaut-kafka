package org.wurdum

import jakarta.inject.Singleton
import java.util.concurrent.ConcurrentHashMap

@Singleton
class WeatherReportRepository {

    private val reports = ConcurrentHashMap<String, WeatherReport>()

    fun save(report: WeatherReport) {
        reports[report.location] = report
    }

    fun findByLocation(location: String): WeatherReport? {
        return reports[location]
    }

    fun findAll(): List<WeatherReport> {
        return reports.values.toList()
    }
}
