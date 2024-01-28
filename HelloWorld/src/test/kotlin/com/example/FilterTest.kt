package com.example

import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.*
import org.http4k.hamkrest.hasHeader
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test

val AddLatency = Filter {next ->
    {
        next(it).header("x-extra-header", "some value")
    }
}

class FilterTest {
    @Test
    fun `フィルターを追加する`(){
        val handler: HttpHandler = AddLatency.then{ Response(Status.OK)}
        val response: Response = handler(Request(Method.GET,"/echo/my+great+message"))
        assertThat(response, hasStatus(Status.OK).and(hasHeader("x-extra-header", "some value")))
    }

}