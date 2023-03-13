package com.example.smarthome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smarthome.ui.ui.login.LoginActivity

class GreetingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_greeting)
    }

    fun goToLogin(view: View){
        val intent = Intent(this, LoginActivity::class.java);
        startActivity(intent);
    }
}