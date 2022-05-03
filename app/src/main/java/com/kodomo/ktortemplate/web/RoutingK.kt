package com.kodomo.ktortemplate.web

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*





internal fun Routing.RoutingK(){

    var count =0;

    get("/get") {
/*
            var users = ""
            transaction {
                Games.select { Games.rid greater 0 }.forEach {
                    users += it[Games.title]+"\n"
                }
            }*/
        count ++
        call.respondText(""+count, contentType = ContentType.Text.Plain)
    }
}