package com.example

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.*
import kotlin.random.Random

val app: HttpHandler = routes(
    "/ping" bind GET to {
        longSleep()
        Response(OK).body("pong")
    },
    "/pong" bind GET to {
        Response(OK).body("ping")
    }
)

fun main() {
//    val printingApp: HttpHandler = PrintRequest().then(app)

    val server = app.asServer(Helidon(9000)).start()

    println("Server started on " + server.port())
}


fun longSleep() {
    val randomMillSecond = Random.nextInt(10,30) * 1000
    println(randomMillSecond)
    Thread.sleep(randomMillSecond.toLong())
}