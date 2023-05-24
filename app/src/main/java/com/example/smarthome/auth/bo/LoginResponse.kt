package com.example.smarthome.auth.bo

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val token: String
)