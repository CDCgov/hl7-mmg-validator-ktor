package com.hl7mmgvalidator

import kotlinx.serialization.*
import kotlinx.serialization.json.*

// Define a data class for vocabulary entries
@Serializable
data class VocabularyEntry(
    val code: String,
    val description: String,
    val source: String
)

// Interface
interface LoaderVocab {

    fun getVocabOnce(): List<VocabularyEntry>

    //Check if an entry exists in the vocabulary list based on the code.
    fun entryExists(code: String): Boolean

}// .LoaderVocab

// Implementation to read vocab from a local file with lazy loading
class LoaderVocabImpl : LoaderVocab {

    // Lazy-loaded vocabulary list
    private val vocabList: List<VocabularyEntry> by lazy {
        loadVocab()
    }// .vocabList

    //Load vocabulary from the JSON file.
    private fun loadVocab(): List<VocabularyEntry> {
        val resourcePath = "vocab/phinvads.json"

        val fileContent = this::class.java.classLoader.getResource(resourcePath)?.readText()
            ?: throw IllegalArgumentException("Resource not found: $resourcePath")

        return Json.decodeFromString(fileContent)
    }// .loadVocab

    // Return the loaded vocabulary list.
    override fun getVocabOnce(): List<VocabularyEntry> {
        return vocabList
    }// .getVocabOnce

    /**
     * Check if an entry exists in the vocabulary list based on the code.
     */
    override fun entryExists(code: String): Boolean {
        // Check if any entry matches the given code
        return vocabList.any { it.code == code }
    }// .entryExists

}// .LoaderVocabImpl
