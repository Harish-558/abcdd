package com.example.bankaiapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        // Inside HomeActivity onCreate
        val username = intent.getStringExtra("USER_NAME")
// In HomeActivity.kt
        // Inside HomeActivity.kt onCreate
        val navProfile = findViewById<ImageButton>(R.id.navProfile)

        val navReels = findViewById<ImageButton>(R.id.navReels)

        navReels.setOnClickListener {
            val intent = Intent(this, ReelsActivity::class.java)
            intent.putExtra("USER_NAME", username)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            try {
                val intent = Intent( this, WelcomeActivity::class.java)
                // Pass the username so the profile page doesn't crash looking for it
                intent.putExtra("USER_NAME", intent.getStringExtra("USER_NAME"))
                startActivity(intent)
            } catch (e: Exception) {
                // This will tell you in the logs if the screen is missing
                Toast.makeText(this, "Profile Screen Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        val storiesContainer = findViewById<LinearLayout>(R.id.storiesContainer)
        val postsContainer = findViewById<LinearLayout>(R.id.postsContainer)

        // Find the Navigation Buttons (Make sure these IDs match your activity_home.xml)
        val navHome = findViewById<ImageButton>(R.id.navHome)
        val navSearch = findViewById<ImageButton>(R.id.navSearch)
        val navMessages = findViewById<ImageButton>(R.id.navMessages)

        Toast.makeText(this, "Welcome to Bankai Home!", Toast.LENGTH_SHORT).show()

        // 1. ADD STORIES
        val friends = listOf("Ichigo", "Rukia", "Renji", "Uryu", "Chad")
        friends.forEach { name ->
            val storyFrame = FrameLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(200, 200).apply {
                    setMargins(15, 0, 15, 0)
                }
                setBackgroundResource(R.drawable.circle_story_border)
            }

            val innerCircle = View(this).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                setBackgroundResource(R.drawable.inner_circle_white)
            }

            val storyText = TextView(this).apply {
                text = name.take(1)
                gravity = Gravity.CENTER
                textSize = 18f
                setTextColor(Color.BLACK)
                setTypeface(null, Typeface.BOLD)
            }

            storyFrame.addView(innerCircle)
            storyFrame.addView(storyText)
            storiesContainer.addView(storyFrame)
        }

        // 2. ADD POSTS
        for (i in 1..59) {
            val postLayout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(0, 20, 0, 40)
            }

            val userName = TextView(this).apply {
                text = "Bankai_Warrior_$i"
                setTypeface(null, Typeface.BOLD)
                setPadding(20, 20, 20, 10)
                setTextColor(Color.BLACK)
                setOnClickListener {
                    startActivity(Intent(this@HomeActivity, ProfileActivity1::class.java))
                }
            }

            navHome.setOnClickListener {
                Toast.makeText(this, "Refreshed Home", Toast.LENGTH_SHORT).show()
            }

            navSearch.setOnClickListener {
                startActivity(Intent(this, SearchActivity::class.java))
            }

            navMessages.setOnClickListener {
                // CHANGE THIS LINE:
                startActivity(Intent(this, FriendsListActivity::class.java))
                startActivity(intent)
            }
            navProfile.setOnClickListener {
                startActivity(Intent(this, ProfileActivity1::class.java))
            }

            navProfile.setOnClickListener {
                startActivity(Intent(this, ProfileActivity1::class.java))
            }

            val postImage = ImageView(this).apply {
                layoutParams =
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 800)
                scaleType = ImageView.ScaleType.CENTER_CROP
                setBackgroundColor(Color.LTGRAY)
            }
            Glide.with(this).load("https://picsum.photos/seed/$i/800/800").into(postImage)

            val interactionLayout = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
            }

            val likeBtn = Button(this).apply {
                text = "🤍 Like"
                setOnClickListener {
                    text = if (text == "🤍 Like") "❤️ Liked" else "🤍 Like"
                }
            }

            val shareBtn = Button(this).apply {
                text = "✈️ Share"
                setBackgroundColor(Color.TRANSPARENT)
                setOnClickListener {
                    startActivity(Intent(this@HomeActivity, ChatActivity::class.java))
                }
            }

            interactionLayout.addView(likeBtn)
            interactionLayout.addView(shareBtn)

            val captionText = TextView(this).apply {
                text = "Bankai_Warrior_$i: Powering up my app! #Step9Done"
                setPadding(20, 10, 20, 20)
                setTextColor(Color.BLACK)
            }

            postLayout.addView(userName)
            postLayout.addView(postImage)
            postLayout.addView(interactionLayout)
            postLayout.addView(captionText)
            postsContainer?.addView(postLayout)
        }

        // 3. NAVIGATION BAR CLICK LISTENERS
        navHome.setOnClickListener {
            Toast.makeText(this, "Refreshed Home", Toast.LENGTH_SHORT).show()
        }

        navSearch.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

// MAKE SURE THIS POINTS TO FRIENDS LIST ONLY
        navMessages.setOnClickListener {
            val intent = Intent(this, FriendsListActivity::class.java)
            startActivity(intent)
        }

        navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity1::class.java))
        }
    }
}