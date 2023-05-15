package com.example.smarthome.ui.data.model

import com.google.gson.annotations.SerializedName

sealed class DataModel {

    data class Light(
        @SerializedName("name")
        val name: String,
        @SerializedName("place")
        val place: String,
        @SerializedName("condition")
        var condition: Boolean,
        @SerializedName("id")
        val id: String? = null,
    ) : DataModel()

    data class Camera(
        @SerializedName("name")
        val name: String,
        @SerializedName("place")
        val place: String,
        @SerializedName("id")
        val id: String? = null,
    ) : DataModel()

    data class Detector(
        @SerializedName("name")
        val name: String,
        @SerializedName("place")
        val place: String,
        @SerializedName("statement")
        val statement: String,
        @SerializedName("id")
        val id: String? = null,
    ) : DataModel()
}