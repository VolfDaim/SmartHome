package com.example.smarthome.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.smarthome.main_activitys.MenuActivity
import com.example.smarthome.R
import com.example.smarthome.auth.bo.LoggedInUser
import com.example.smarthome.httpclient.ApiInterface
import com.example.smarthome.httpclient.ApiService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import retrofit2.Call
import retrofit2.Response

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

            val login = ApiInterface.create().login(token = jwt)
            login.enqueue(object :
                retrofit2.Callback<LoggedInUser> {
                override fun onResponse(
                    call: Call<LoggedInUser>,
                    response: Response<LoggedInUser>
                ) {
                    if(response.body() == null){
                        Toast.makeText(this@LoginActivity, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                    } else {
                        getSharedPreferences("shared preferences", Context.MODE_PRIVATE).edit()
                            .putString("token", jwt).putString("username", response.body()!!.name).apply()

                        val prefs = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
                        val token = prefs?.getString("token", null)
                        val editor = prefs?.edit()

                        ApiService().getLights(token!!, editor!!, this@LoginActivity)
                        ApiService().getCameras(token, editor, this@LoginActivity)
                        ApiService().getDetectors(token, editor, this@LoginActivity)

                        val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(call: Call<LoggedInUser>, t: Throwable) {
                    Toast.makeText(this@LoginActivity, "Response Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }

        signUp.setOnClickListener {
            Toast.makeText(this@LoginActivity, "Sign up", Toast.LENGTH_SHORT).show()
        }


    }
}