package com.example.bankaiapp

import android.view.View
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class FriendsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)

// Inside onCreate...
        val btnOpenProfile = findViewById<Button>(R.id.btnOpenProfile)
        btnOpenProfile.setOnClickListener {
            // This 'Intent' is the bridge between screens
            val intent = Intent(this, ProfileActivity1::class.java)
            startActivity(intent)
        }

        // --- 1. BACK BUTTON LOGIC ---
        // We find the button from the XML we updated earlier
        val btnBack = findViewById<ImageButton>(R.id.btnBackFriends)
        btnBack.setOnClickListener {
            finish() // Closes this screen and goes back to Home
        }

        // 2. FIND THE CONTAINER
        val container = findViewById<LinearLayout>(R.id.friendsContainer)


        // 3. FRIENDS DATA MAP
        val friends = mapOf(
            "Ichigo" to "https://picsum.photos/seed/ichigo/200/200",
            "Rukia" to "https://picsum.photos/seed/rukia/200/200",
            "Renji" to "https://picsum.photos/seed/renji/200/200",
            "Uryu" to "https://picsum.photos/seed/uryu/200/200",
            "Chad" to "https://picsum.photos/seed/chad/200/200",
            "Byakuya" to "https://picsum.photos/seed/byakuya/200/200"
        )

        // 4. LOOP THROUGH AND BUILD THE LIST
        friends.forEach { (name, imageUrl) ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(20, 30, 20, 30)
                gravity = Gravity.CENTER_VERTICAL
                setBackgroundResource(android.R.drawable.list_selector_background)

                setOnClickListener {
                    val intent = Intent(this@FriendsListActivity, ChatActivity::class.java)
                    intent.putExtra("FRIEND_NAME", name)
                    startActivity(intent)
                }
            }

            // A. Profile Image
            val profileImage = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(120, 120).apply {
                    setMargins(0, 0, 30, 0)
                }
            }

            Glide.with(this)
                .load(imageUrl)
                .circleCrop()
                .into(profileImage)

            // B. Name Text
            val nameText = TextView(this).apply {
                text = name
                textSize = 18f
                setTextColor(Color.BLACK)
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val statusDot = View(this).apply {
                layoutParams = LinearLayout.LayoutParams(25, 25).apply {
                    setMargins(15, 0, 15, 0)
                }
                setBackgroundResource(R.drawable.status_online)
            }
            // C. Arrow Icon
            val arrow = TextView(this).apply {
                text = ">"
                setTextColor(Color.GRAY)
                textSize = 20f
            }


            // D. Assemble the Row
            row.addView(profileImage)
            row.addView(nameText)
            row.addView(statusDot)
            row.addView(arrow)

            container.addView(row)
        }
        // 1. Find the search box
        val etFriendsSearch = findViewById<EditText>(R.id.etFriendsSearch)

// 2. Add the filtering logic
        etFriendsSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()

                // Loop through the rows in your container
                for (i in 0 until container.childCount) {
                    val row = container.getChildAt(i) as LinearLayout
                    // The name is the second child (index 1) because the image is first (index 0)
                    val nameText = (row.getChildAt(1) as TextView).text.toString().lowercase()

                    row.visibility = if (nameText.contains(query)) android.view.View.VISIBLE else android.view.View.GONE
                }
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })
    }
}