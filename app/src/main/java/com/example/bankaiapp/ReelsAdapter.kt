package com.example.bankaiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Replace your current class header with this:
class ReelsAdapter(private val videoUrls: List<String>) : RecyclerView.Adapter<ReelsAdapter.ReelViewHolder>() {
    class ReelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.reelTitle)
        val playerView: androidx.media3.ui.PlayerView = view.findViewById(R.id.playerView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_reel, parent, false)
        return ReelViewHolder(view)
    }

    // THE UPDATED BLOCK IS HERE
    // --- STEP 2: Replace your old onBindViewHolder with this ---
    override fun onBindViewHolder(holder: ReelViewHolder, position: Int) {
        // 1. Get the specific URL for THIS reel based on the position
        val currentVideoUrl = videoUrls[position]

        // 2. Set the Title (you can use the URL or a custom name)
        holder.title.text = "Bankai Reel #${position + 1}"

        // 3. Initialize the player for this specific item
        val player = androidx.media3.exoplayer.ExoPlayer.Builder(holder.itemView.context).build()
        holder.playerView.player = player

        // 4. Load the video from the URL we got in step 1
        val mediaItem = androidx.media3.common.MediaItem.fromUri(currentVideoUrl)
        player.setMediaItem(mediaItem)

        player.prepare()
        player.playWhenReady = true // Auto-play when user swipes to it
    }
    override fun getItemCount(): Int = videoUrls.size
    override fun onViewDetachedFromWindow(holder: ReelViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.playerView.player?.release()
    }
}