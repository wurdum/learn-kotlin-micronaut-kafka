package org.wurdum

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.serde.annotation.Serdeable


@Controller("/hello")
class HelloController {

    @Get
    fun index(): HttpResponse<Greeting> {
        val greeting = Greeting(message = "Hello World")
        return HttpResponse.ok(greeting)
    }
}

@Serdeable
data class Greeting(val message: String)
