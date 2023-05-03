package com.example.smarthome.ui.data.listClasses

import com.google.gson.annotations.SerializedName

data class Device(
    @SerializedName("name")
    val name: String,
    @SerializedName("place")
    val place: String
)
