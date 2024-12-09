package com.hl7mmgvalidator

import java.io.File

// Interface
interface LoaderVocab {

    fun getVocab(): String

}// .LoaderVocab

// Implementation read vocab from local file read
class LoaderVocabImpl : LoaderVocab {

    override fun getVocab(): String {

        // Read the resource file from the classpath
        val resourcePath = "vocab/phinvads.json"

        val fileContent = this::class.java.classLoader.getResource(resourcePath)?.readText()
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")

        return fileContent
    }// .getVocab 

}// .LoaderVocabImpl 
