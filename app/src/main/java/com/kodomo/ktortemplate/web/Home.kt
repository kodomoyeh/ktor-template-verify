package com.kodomo.ktortemplate.web

import kotlinx.html.*
import io.ktor.http.*

import io.ktor.server.application.*
import io.ktor.server.freemarker.FreeMarker
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.html.*
import io.ktor.server.mustache.*

import io.ktor.server.pebble.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.title

import java.util.*

data class User(val id: Int, val name: String)


internal fun Routing.Home(){

    var count =0;
    ///data/data/{Package Name}/databases/{database name}.db

    get("/fr") {

        count ++
        val sampleUser = User(count, "John")
        //call.respond(PebbleContent("index.html", mapOf("user" to sampleUser), Locale("zh-Hant-TW"),"e"))
        call.respond(FreeMarkerContent("index.ftl", mapOf("user" to sampleUser)))
    }

    get("/") {
        val name = "Ktor"
        call.respondHtml(HttpStatusCode.OK) {
            head {
                title {
                    "主選單"
                }
                script(type = ScriptType.textJavaScript) {
                    unsafe {
                        raw("""
            function my() { return 1; }
            """)
                    }
                }
            }
            body {
                h1 {
                    +"Hello from $name!"
                }
            }
        }
    }

    get("/pe") {

        count ++
        val sampleUser = User(count, "John")
        call.respond(PebbleContent("index.html", mapOf("user" to sampleUser), Locale("zh-Hant-TW"),"e"))
        //call.respond(FreeMarkerContent("index.ftl", mapOf("user" to sampleUser)))
    }

    get("/mu") {
        count++
        val sampleUser = User(count, "John")
        call.respond(MustacheContent("index.html", mapOf("user" to sampleUser)))
    }
}