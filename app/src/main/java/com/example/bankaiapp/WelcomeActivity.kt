package com.example.bankaiapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView // Don't miss this!
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Catch the name
        val userName = intent.getStringExtra("USER_NAME")

        // Update the text
        val tvUser = findViewById<TextView>(R.id.tvUserEmail)
        tvUser.text = "Welcome, ${userName ?: "Soul Reaper"}"


        val btnGoHome = findViewById<Button>(R.id.btnGoHome)
        btnGoHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USER_NAME", userName)
            startActivity(intent)
        }
    }
}