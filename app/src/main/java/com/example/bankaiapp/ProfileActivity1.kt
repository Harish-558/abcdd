package com.example.bankaiapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile1)

        // 1. Find the Views
        val tvName = findViewById<TextView>(R.id.tvProfileName)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // 2. Set your specific name
        tvName.text = "Ichigo Kurosaki"

        // 3. Make the Back button work
        btnBack.setOnClickListener {
            finish() // This closes this screen and takes you back to the list
        }
    }
}