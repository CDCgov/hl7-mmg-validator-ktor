package com.hl7mmgvalidator

import java.io.File

// Interface
interface LoaderMmg {

    fun getMmg(mmgType: String): String

}// .LoaderMmg

// Implementation read MMG from local file read
class LoaderMmgImpl : LoaderMmg {

    override fun getMmg(mmgType: String): String {

        // Read the resource file from the classpath
        val resourcePath = mmgType

        val fileContent = this::class.java.classLoader.getResource(resourcePath)?.readText()
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")

        return fileContent
    }// .getMmg 

}// .LoaderMmgImpl 
