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
        val response = researchProjectsUsecase().execute()
        Response(OK).with(
            scientificResearch.of(response)
        )
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

    val extracted = scientificResearch(response)

    println(extracted)

//    println(response)

    // We can use the auto method here from either Jackson, Gson or the Xml message format objects.
    // Note that the auto() method needs to be manually imported as IntelliJ won't pick it up automatically.
//    val messageLens = Body.auto<Message>().toLens()
//
//    val myMessage = Message("hello", Email("bob@git.com"), Email("sue@git.com"))
//
//    // to inject the body into the message - this also works with Response
//    val requestWithEmail = messageLens(myMessage, Request(GET, "/pong"))
//
//    println(requestWithEmail)

// Produces:
//    GET / HTTP/1.1
//    content-type: application/json
//
//    {"subject":"hello","from":{"value":"bob@git.com"},"to":{"value":"sue@git.com"}}

//    // to extract the body from the message - this also works with Response
//    val extractedMessage = messageLens(requestWithEmail)
//
//    println(extractedMessage)
//    println(extractedMessage == myMessage)

// Produces:
//    Message(subject=hello, from=Email(value=bob@git.com), to=Email(value=sue@git.com))
//    true

//    println("Server started on " + server.port())
}


fun longSleep() {
    val randomMillSecond = Random.nextInt(10,30) * 1000
    println(randomMillSecond)
    Thread.sleep(randomMillSecond.toLong())
}