package com.example.smarthome.main_activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smarthome.R
import com.example.smarthome.auth.LoginActivity

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