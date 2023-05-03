package com.example.smarthome.ui.data.listClasses

import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("name")
    val name: String,
    @SerializedName("place")
    val place: String
)