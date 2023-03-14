package com.example.smarthome.ui.data

import com.example.smarthome.ui.data.model.LoggedInUser
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.loopj.android.http.RequestParams
import cz.msebera.android.httpclient.Header
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            val params = RequestParams()
            params.put("username", username)
            params.put("password", password)

            val client = AsyncHttpClient()
            val url = "192.168.1.205:2000/login"

            client[url, params, object : AsyncHttpResponseHandler(){
                override fun onStart(){}

                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<Header>,
                    responseBody: ByteArray
                ){
                    println(String(responseBody))
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseBody: ByteArray?,
                    error: Throwable?
                ) {
                    TODO("Not yet implemented")
                }
            }
            ]
            if (username == "dima" && password == "123") {
                val fakeUser = LoggedInUser(username, password)
                return Result.Success(fakeUser)
            } else {
                return Result.Error(IOException("Error logging in"))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}