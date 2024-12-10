package com.hl7mmgvalidator.loadervocab

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// Interface
interface LoaderVocab {

    fun entryExists(vocabKey: String, code: String): Boolean
}// .LoaderVocab

// vocabulary entries
data class VocabularyEntry(

    val code: String,
    val description: String,
    val source: String
)// .VocabularyEntry

// Singleton object to hold the vocabulary map
object VocabSingleton {
    private var vocabMap: Map<String, List<VocabularyEntry>>? = null

    fun getVocab(): Map<String, List<VocabularyEntry>> {

        if (vocabMap == null) {
            // Load the vocabulary from the JSON file once
            val resourcePath = "vocab/phinvads.json"
            val fileContent = this::class.java.classLoader.getResource(resourcePath)?.readText()
                ?: throw IllegalArgumentException("Resource not found: $resourcePath")

            val type: Type = object : TypeToken<Map<String, List<VocabularyEntry>>>() {}.type
            vocabMap = Gson().fromJson(fileContent, type)
        }// .if

        return vocabMap!!
    }// .getVocab
}// .VocabSingleton

// Implementation
class LoaderVocabImpl : LoaderVocab {

    override fun entryExists(vocabKey: String, code: String): Boolean {

        val vocab = VocabSingleton.getVocab()

        // Check key is in the vocab
        if (!vocab.containsKey(vocabKey)) {
            return false
        }// .if

        // check entry
        val entryExists = vocab[vocabKey]?.any { it.code == code } == true
        // println("Checking if entry exists for key --> $vocabKey, code --> $code, result --> $entryExists")
        return entryExists
    }// .entryExists

}// .LoaderVocabImpl

