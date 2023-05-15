package com.example.smarthome.ui.data.listClasses

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("data")
    val userInfo: UserInfo,
    val message: String
)

data class UserInfo(
    val _id: String,
    val email_address: String
)