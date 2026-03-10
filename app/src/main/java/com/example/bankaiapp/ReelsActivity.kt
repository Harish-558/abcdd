package com.example.bankaiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2

class ReelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reels)

        // 1. Create a list of titles for your "Many Reels"
        // (Later, we can replace these with actual video links)
        val videoList = listOf(
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
            "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4"
        )

        // 2. Find the ViewPager2 from your activity_reels.xml
        val viewPager = findViewById<ViewPager2>(R.id.viewPagerReels)

        // 3. Set the Adapter and pass the videoList to it
        val adapter = ReelsAdapter(videoList)
        viewPager.adapter = adapter

        // 4. Set orientation to Vertical for TikTok-style swiping
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        // --- PASTE UNTIL HERE ---
    }
}