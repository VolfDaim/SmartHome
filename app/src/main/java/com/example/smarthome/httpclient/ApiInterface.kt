package com.example.smarthome.httpclient

import com.example.smarthome.ui.data.listClasses.Echo
import com.example.smarthome.ui.data.listClasses.Light
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("devices")
    fun getDevices() : Call<List<Light>>

    @GET("home")
    fun getEcho() : Call<Echo>

    companion object {

        var BASE_URL = "https://6c74-188-123-231-121.eu.ngrok.io"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}