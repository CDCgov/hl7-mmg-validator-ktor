package com.hl7mmgvalidator.loadermmg

import com.google.gson.annotations.SerializedName

// MMG Data Class
data class MMG(
    @SerializedName("id") val id: String,
    @SerializedName("guideStatus") val guideStatus: String,
    @SerializedName("name") val name: String,
    @SerializedName("shortName") val shortName: String,
    @SerializedName("blocks") val blocks: List<Block>
)

// Block Data Class
data class Block(
    @SerializedName("id") val id: String,
    @SerializedName("ordinal") val ordinal: Int,
    @SerializedName("type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("elements") val elements: List<Element>
)

// Element Data Class
data class Element(
    @SerializedName("ordinal") val ordinal: Int,
    @SerializedName("name") val name: String,
    @SerializedName("dataType") val dataType: String,
    @SerializedName("isUnitOfMeasure") val isUnitOfMeasure: Boolean,
    @SerializedName("priority") val priority: String,
    @SerializedName("isRepeat") val isRepeat: Boolean,
    @SerializedName("repetitions") val repetitions: Int,
    @SerializedName("mayRepeat") val mayRepeat: String,
    @SerializedName("valueSetCode") val valueSetCode: String?,
    @SerializedName("valueSetVersionNumber") val valueSetVersionNumber: Int?,
    @SerializedName("codeSystem") val codeSystem: String?,
    @SerializedName("mappings") val mappings: Mapping
) {
    val path: String = when (mappings.hl7v251.segmentType) {
        "OBX" -> {
            var p = "${mappings.hl7v251.segmentType}[@3.1='${mappings.hl7v251.identifier}']-5"
            when {
                mappings.hl7v251.dataType == "CE" || mappings.hl7v251.dataType == "CWE" -> p += ".1"
                mappings.hl7v251.dataType == "SN" -> p += ".2"
            }
            p
        }
        else -> {
            var path = "${mappings.hl7v251.segmentType}-${mappings.hl7v251.fieldPosition}"
            if (mappings.hl7v251.componentPosition != -1)
                path += ".${mappings.hl7v251.componentPosition}"
            path
        }
    }
}

// Mapping Data Class
data class Mapping(
    @SerializedName("hl7v251") val hl7v251: HL7Mapping
)

// HL7Mapping Data Class
data class HL7Mapping(
    @SerializedName("legacyIdentifier") val legacyIdentifier: String,
    @SerializedName("identifier") val identifier: String,
    @SerializedName("dataType") val dataType: String,
    @SerializedName("segmentType") val segmentType: String,
    @SerializedName("orbPosition") val orbPosition: Int,
    @SerializedName("fieldPosition") val fieldPosition: Int,
    @SerializedName("componentPosition") val componentPosition: Int,
    @SerializedName("usage") val usage: String,
    @SerializedName("cardinality") val cardinality: String,
    @SerializedName("repeatingGroupElementType") val repeatingGroupElementType: String
) {
    val path: String = when (segmentType) {
        "OBX" -> {
            var p = "$segmentType[@3.1='${identifier}']-5"
            when {
                dataType == "CE" || dataType == "CWE" -> p += ".1"
                dataType == "SN" -> p += ".2"
            }
            p
        }
        else -> {
            var path = "$segmentType-$fieldPosition"
            if (componentPosition != -1)
                path += ".$componentPosition"
            path
        }
    }
}
