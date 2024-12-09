package com.hl7mmgvalidator

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}// .main 

fun Application.module() {
    configureRouting()
}// .app 
    