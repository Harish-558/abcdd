package com.example.bankaiapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val btnBack = findViewById<ImageButton>(R.id.btnBackSearch)
        btnBack.setOnClickListener {
            finish() // This "kills" the Search page and takes you back to Home
        }
        val container = findViewById<LinearLayout>(R.id.searchResultContainer)
        val warriors = listOf("Byakuya", "Kenpachi", "Toshiro", "Grimmjow", "Ulquiorra", "Aizen")

        warriors.forEach { name ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(0, 20, 0, 20)
                setOnClickListener {
                    // Navigate to profile when a name is clicked
                    startActivity(Intent(this@SearchActivity, ProfileActivity1::class.java))
                }
            }
            // 1. Inside your onCreate, find the EditText
            val etSearch = findViewById<EditText>(R.id.etSearch)

// 2. Add the "Watcher" logic
            etSearch.addTextChangedListener(object : android.text.TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val query = s.toString().lowercase()

                    // Loop through all the rows in your container
                    for (i in 0 until container.childCount) {
                        val row = container.getChildAt(i) as LinearLayout
                        val nameText = (row.getChildAt(0) as TextView).text.toString().lowercase()

                        // If the name contains what you typed, show it. Otherwise, hide it!
                        if (nameText.contains(query)) {
                            row.visibility = android.view.View.VISIBLE
                        } else {
                            row.visibility = android.view.View.GONE
                        }
                    }
                }
                override fun afterTextChanged(s: android.text.Editable?) {}
            })

            val nameText = TextView(this).apply {
                text = name
                textSize = 18f
                        layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val followBtn = Button(this).apply {
                text = "Follow"
                setOnClickListener {
                    text = "Following"
                    alpha = 0.5f
                }
            }

            row.addView(nameText)
            row.addView(followBtn)
            container.addView(row)
        }
    }
}