package com.example.smarthome.ui.data.listClasses

import com.google.gson.annotations.SerializedName

data class Detector (
    @SerializedName("name")
    val name: String,
    @SerializedName("place")
    val place: String,
    @SerializedName("statement")
    val statement: String
)