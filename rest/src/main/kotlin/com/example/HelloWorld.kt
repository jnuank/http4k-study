package com.example

import org.http4k.client.HelidonClient
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.DebuggingFilters
import org.http4k.format.Jackson.auto
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.*
import kotlin.random.Random

val scientificResearch = Body.auto<ResearchProjectsDto>().toLens()

val app: HttpHandler = routes(
    "/ping" bind GET to {
        longSleep()
        Response(OK).body("pong")
    },
    "/pong" bind GET to {
        Response(OK).body("ping")
    },
    "/research" bind GET to {
        val response = ResearchProjectsUsecase().execute()
        // ResponseはHttpMessageを実装する
        val handler = Response(OK).with(
            // Withは(Response) -> Response
            // ↓は(Response) -> Response
            // ↓は scientificResearch.of<Response>(response)ということ。withでResponse型に決まる
            scientificResearch.of(response)
        )
        handler
    }
)
data class Email(val value: String)
data class Message(val subject: String, val from: Email, val to: Email)

fun main() {
//    val printingApp: HttpHandler = ResponseFilters
//                                    .then(app)
    val server = app.asServer(Helidon(9000)).start()

    val request = Request(GET, "http://localhost:9000/research")
    val client = DebuggingFilters.PrintResponse().then(HelidonClient())


    val response = client(request)

    val extracted = scientificResearch.extract(response)

    println(extracted)
}


fun longSleep() {
    val randomMillSecond = Random.nextInt(10,30) * 1000
    println(randomMillSecond)
    Thread.sleep(randomMillSecond.toLong())
}