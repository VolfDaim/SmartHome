package com.example.smarthome.httpclient

import com.example.smarthome.ui.data.listClasses.*
import com.example.smarthome.ui.data.model.DataModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    @GET("home")
    fun getEcho() : Call<Echo>

    /**
     * Ендпоинты для работы с лампочками.
     */
    @GET("api/devices/lights")
    fun getAllLights() : Call<List<DataModel.Light>>

    @GET("api/devices/lights/{id}")
    fun getLight(@Path("id") id: String) : Call<DataModel.Light>

    @POST("api/devices/lights")
    fun createLight(@Body light: DataModel.Light) : Call<DataModel.Light>

    @PUT("api/devices/lights/{id}")
    fun updateLight(@Path("id") id: String) : Call<DataModel.Light>

    @DELETE("api/devices/lights/{id}")
    fun deleteLight(@Path("id") id: String) : Call<DataModel.Light>

    @POST("api/devices/lights/{id}/toggle")
    fun toggleLight(@Path("id") id: String) : Call<DataModel.Light>

    /**
     * Ендпоинты для работы с камерами.
     */
    @GET("api/devices/cameras")
    fun getAllCameras() : Call<List<DataModel.Camera>>

    @GET("api/devices/cameras/{id}")
    fun getCamera(@Path("id") id: String) : Call<DataModel.Camera>

    @POST("api/devices/cameras")
    fun createCamera(@Body camera: DataModel.Camera) : Call<DataModel.Camera>

    @PUT("api/devices/cameras/{id}")
    fun updateCamera(@Path("id") id: String) : Call<DataModel.Camera>

    @DELETE("api/devices/cameras/{id}")
    fun deleteCamera(@Path("id") id: String) : Call<DataModel.Camera>

    /**
     * Ендпоинты для работы с датчками.
     */
    @GET("api/devices/detectors")
    fun getAllDetectors() : Call<List<DataModel.Detector>>

    @GET("api/devices/detectors/{id}")
    fun getDetector(@Path("id") id: String) : Call<DataModel.Detector>

    @POST("api/devices/detectors")
    fun createDetector(@Body detector: DataModel.Detector) : Call<DataModel.Detector>

    @PUT("api/devices/detectors/{id}")
    fun updateDetector(@Path("id") id: String) : Call<DataModel.Detector>

    @DELETE("api/devices/detectors/{id}")
    fun deleteDetector(@Path("id") id: String) : Call<DataModel.Detector>

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