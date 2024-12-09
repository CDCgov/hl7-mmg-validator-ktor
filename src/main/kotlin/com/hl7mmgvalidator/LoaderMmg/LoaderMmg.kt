package com.hl7mmgvalidator.loadermmg

import java.io.File

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Interface
interface LoaderMmg {

    // Used for example only
    fun getGenV2Mmg(): MMG

    // TODO: this is needed for the MMG validation 
    // fun getMMGs(
    //     genVProfile: String,
    //     conditionProfile: String,
    //     eventCode: String,
    //     jurisdictionCode: String
    // ): List<MMG> 

}// .LoaderMmg

// Implementation read MMG from local file read
class LoaderMmgImpl : LoaderMmg {

    override fun getGenV2Mmg(): MMG {

        // Read the resource file from the classpath
        val resourcePath = "mmgs/generic_version_2_0_1.json"

        val fileContent = this::class.java.classLoader.getResource(resourcePath)?.readText()
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")

        // Deserialize JSON to MMG object
        val mmg = Gson().fromJson(fileContent, MMG::class.java)
        // println("Deserialized MMG: $mmg")

        return mmg 
    }// .getGenV2Mmg 

}// .LoaderMmgImpl 
