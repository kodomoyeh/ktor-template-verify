package com.kodomo.ktortemplate

import android.app.Service

import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.github.mustachejava.DefaultMustacheFactory
import com.kodomo.ktortemplate.web.Home
import com.kodomo.ktortemplate.web.RoutingK
import com.mitchellbosecke.pebble.loader.ClasspathLoader
import freemarker.cache.ClassTemplateLoader

//import freemarker.cache.ClassTemplateLoader


import io.ktor.server.application.*
import io.ktor.server.plugins.*

import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.freemarker.FreeMarker
import io.ktor.server.mustache.*

import io.ktor.server.netty.*
import io.ktor.server.pebble.*
import io.ktor.server.response.*
import io.netty.util.internal.logging.InternalLoggerFactory
import io.netty.util.internal.logging.JdkLoggerFactory

import org.slf4j.LoggerFactory
import kotlin.coroutines.EmptyCoroutineContext

class HttpServer() : Service() {

    companion object {
        const val HTTP_PORT = 8080
    }

    private val logger = LoggerFactory.getLogger(HttpServer::class.java.simpleName)
    val environment = applicationEngineEnvironment {

        this.log = logger

        connector {
            this.port = HttpServer.HTTP_PORT
        }

        module(Application::landingModule)

    }
    override fun onCreate() {
        super.onCreate()
        Log.d("onCreate", "starting service HttpService...")
        Thread {
            InternalLoggerFactory.setDefaultFactory(JdkLoggerFactory.INSTANCE)
            embeddedServer(Netty, environment) {

            }.start(wait = true)
        }.start()
    }

    override fun onBind(intent: Intent): IBinder? = null
}


fun Application.landingModule() {
    //val race = Race(this. , 1)
    install(Pebble) {
        loader(ClasspathLoader().apply {
            prefix = "templates"
        })

    }
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }
    install(Routing)
    {
        RoutingK()
        Home()
    }
}

