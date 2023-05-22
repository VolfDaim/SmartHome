package com.example.smarthome.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthome.Menu
import com.example.smarthome.R
import com.example.smarthome.data.model.LoggedInUser
import com.example.smarthome.httpclient.ApiInterface
import com.example.smarthome.ui.data.listClasses.LoginResponse
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.login.LoginException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editLogin: EditText = findViewById(R.id.edit_login)
        val editPassword: EditText = findViewById(R.id.edit_password)

        val signUp: TextView = findViewById(R.id.sign_up)
        val signIn: Button = findViewById(R.id.sign_in)

        signIn.setOnClickListener {
            val jwt: String = Jwts.builder()
                .claim("username", editLogin.text.toString())
                .claim("password", editPassword.text.toString())
                .signWith(SignatureAlgorithm.HS256, "secret".toByteArray())
                .compact()
            println(jwt)
            val login = ApiInterface.create().login(token = jwt)
            login.enqueue(object :
                retrofit2.Callback<LoggedInUser> {
                override fun onResponse(
                    call: Call<LoggedInUser>,
                    response: Response<LoggedInUser>
                ) {
                    getSharedPreferences("shared preferences", Context.MODE_PRIVATE).edit().putString("token", jwt).apply()
                    println(response.body().toString())
                    val intent = Intent(this@LoginActivity, Menu::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onFailure(call: Call<LoggedInUser>, t: Throwable) {
                    println(t.message.toString())
                    Toast.makeText(this@LoginActivity, "Response Failed", Toast.LENGTH_SHORT).show()
                    getSharedPreferences("shared preferences", Context.MODE_PRIVATE).edit().putString("token", jwt).apply()
                }

            })
        }

        signUp.setOnClickListener {

        }


    }
}