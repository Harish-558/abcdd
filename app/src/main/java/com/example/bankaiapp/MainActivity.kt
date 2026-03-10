package com.example.bankaiapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val etUser = findViewById<EditText>(R.id.etUsername)
        val etPass = findViewById<EditText>(R.id.etPassword)

        btnLogin.setOnClickListener {
            val username = etUser.text.toString()
            val password = etPass.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                // 1. Visual feedback for the user
                btnLogin.setBackgroundColor(Color.GREEN)
                btnLogin.text = "Logging in..."
                Toast.makeText(this, "Welcome to Bankaiapp", Toast.LENGTH_SHORT).show()

                // 2. Navigation with a safety check
                btnLogin.postDelayed({
                    try {
                        val intent = Intent(this@MainActivity, HomeActivity::class.java)
                        intent.putExtra("USER_NAME", username)
                        startActivity(intent)
                        finish()
                    } catch (e: Exception) {
                        // This helps find the "Silent Crash" if HomeActivity isn't in Manifest
                        Toast.makeText(this@MainActivity, "Navigation Error: ${e.message}", Toast.LENGTH_LONG).show()
                        btnLogin.setBackgroundColor(Color.RED)
                        btnLogin.text = "Retry Login"
                    }
                }, 2000)
            } else {
                // 3. Handles empty fields
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}