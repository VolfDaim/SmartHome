package com.example.smarthome.httpclient

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitServices {
    @POST("auth/sign-in")
    fun SignIn(@Body request: LoginRequest): Call<LoginResponse>
}