package com.example.smarthome.httpclient

import com.example.smarthome.auth.bo.LoginResponse
import com.example.smarthome.auth.bo.LoggedInUser
import com.example.smarthome.ui.fragment_home.bo.DataModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {

    /**
     * Ендпоинты для авторизации и регистрации.
     */
    @POST("auth/sign-in")
    fun login(@Header("Authorization") token: String): Call<LoggedInUser>

    /**
     * Ендпоинты для работы с лампочками.
     */
    @GET("api/devices/lights/")
    fun getAllLights(@Header("Authorization") token: String?) : Call<ArrayList<DataModel.Light>>

    @POST("api/devices/lights/")
    fun createLight(@Header("Authorization") token: String?, @Body light: DataModel.Light) : Call<DataModel.Light>

    @DELETE("api/devices/lights/{id}")
    fun deleteLight(@Header("Authorization") token: String?, @Path("id") id: String) : Call<DataModel.Light>

    @POST("api/devices/lights/{id}/toggle/")
    fun toggleLight(@Header("Authorization") token: String?, @Path("id") id: String?) : Call<DataModel.Light>

    /**
     * Ендпоинты для работы с камерами.
     */
    @GET("/api/devices/cameras/")
    fun getAllCameras(@Header("Authorization") token: String?) : Call<ArrayList<DataModel.Camera>>

    @POST("api/devices/cameras/")
    fun createCamera(@Header("Authorization") token: String?, @Body camera: DataModel.Camera) : Call<DataModel.Camera>

    @DELETE("api/devices/cameras/{id}")
    fun deleteCamera(@Header("Authorization") token: String?, @Path("id") id: String) : Call<DataModel.Camera>

    /**
     * Ендпоинты для работы с датчками.
     */
    @GET("/api/devices/detectors/")
    fun getAllDetectors(@Header("Authorization") token: String?) : Call<ArrayList<DataModel.Detector>>

    @POST("api/devices/detectors/")
    fun createDetector(@Header("Authorization") token: String?, @Body detector: DataModel.Detector) : Call<DataModel.Detector>

    @DELETE("api/devices/detectors/{id}")
    fun deleteDetector(@Header("Authorization") token: String?, @Path("id") id: String) : Call<DataModel.Detector>

    companion object {

        var BASE_URL = "https://3d40-188-123-231-103.eu.ngrok.io"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}