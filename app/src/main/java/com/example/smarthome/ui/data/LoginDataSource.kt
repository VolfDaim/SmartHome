package com.example.smarthome.ui.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.smarthome.httpclient.LoginRequest
import com.example.smarthome.httpclient.LoginResponse
import com.example.smarthome.httpclient.RetrofitClient
import com.example.smarthome.httpclient.RetrofitServices
import com.example.smarthome.ui.data.model.LoggedInUser
import com.google.gson.JsonObject
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import mu.withLoggingContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*
import java.lang.Exception
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Executors

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {


    fun logout() {
        // TODO: revoke authentication
    }
}