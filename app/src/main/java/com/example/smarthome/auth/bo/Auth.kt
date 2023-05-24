package com.example.smarthome.auth.bo

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("email")
    val email: String,
    val password: String
)