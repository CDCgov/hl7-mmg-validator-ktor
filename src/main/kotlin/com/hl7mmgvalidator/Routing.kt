package com.hl7mmgvalidator

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import io.ktor.server.request.*
import com.hl7mmgvalidator.validatormmg.*
import com.hl7mmgvalidator.loadermmg.*
import com.hl7mmgvalidator.loadervocab.*

import com.google.gson.Gson


fun Application.configureRouting() {
    routing {

        get("/") {
            call.respondText("HL7 MMG Validator Ktor")
        }// .get

        post("/validate") {

            // *************************************************************************
            // Dependency 1: Gets the respective MMGs as needed from the message
            // TODO: Implement the logic to read the MMGs based on the parameters 
            // and make all the MMGs available
            // *************************************************************************
            val loaderMmg: LoaderMmg = LoaderMmgImpl()

            // *************************************************************************
            // Dependency 2: Based on the PhinVads vocabulary can check HL7 message content
            // TODO: Implement the logic to check against the real vocabulary
            // *************************************************************************
            val loaderVocab: LoaderVocab = LoaderVocabImpl()
          
            try {

                // *************************************************************************
                // Read the body of the POST request as text, this should be the HL7 message
                // *************************************************************************
                val hl7Text = call.receiveText()
                log.info("Received body: $hl7Text")

                // *************************************************************************
                // Load the MMG Validator with Dependency: 1. (loaderMmg) and 2. (loaderVocab)
                // *************************************************************************
                val validatorMmg = MmgValidator(loaderMmg, loaderVocab)

                // *************************************************************************
                // MMG Validation Report 
                // *************************************************************************
                val validationReport = validatorMmg.validate(hl7Text)

                call.respond(Gson().toJson(validationReport))
            } catch (e: Exception) {
                
                // return error
                call.respondText("Error: ${e.message}")
            }// .try-catch

        }// .post

    }// .routing
}
// .Application
