package com.hl7mmgvalidator

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import io.ktor.server.request.*

fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("HL7 MMG Validator Ktor")
        }// .get

        post("/validate") {

            // *************************************************************************
            // Dependency 1: Get the respective MMG as needed from the message
            // *************************************************************************
            val loaderMmg: LoaderMmg = LoaderMmgImpl()
            val mmgType = "mmgs/genv2.json" // TODO: mmgType should come from reading the message

            // *************************************************************************
            // Dependency 2: Get the PhinVads Vocab
            // *************************************************************************
            val loaderVocab: LoaderVocab = LoaderVocabImpl()
          

            try {
                val mmgJson = loaderMmg.getMmg(mmgType)
                val vocabList = loaderVocab.getVocabOnce()

                // *************************************************************************
                // Read the body of the POST request as text
                // *************************************************************************
                val hl7Text = call.receiveText()
                log.info("Received body: $hl7Text")


                call.respondText(vocabList.toString())
            } catch (e: Exception) {
                // return error
                call.respondText("Error: ${e.message}")
            }// .try-catch

        }// .post

    }// .routing
}
// .Application
