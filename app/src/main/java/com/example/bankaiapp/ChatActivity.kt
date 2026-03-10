package com.example.bankaiapp

import android.view.Gravity
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)



        fun addBotMessage(message: String, container: LinearLayout) {
            val textView = TextView(this).apply {
                text = message
                setPadding(30, 20, 30, 20)
                setTextColor(Color.BLACK)
                setBackgroundResource(R.drawable.chat_bubble_bot) // Use the gray bubble
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(20, 10, 100, 10) // Leave space on the right
                }
            }
            container.addView(textView)
        }

        fun addUserMessage(message: String, container: LinearLayout) {
            val textView = TextView(this).apply {
                text = message
                setPadding(30, 20, 30, 20)
                setTextColor(Color.WHITE)
                setBackgroundResource(R.drawable.chat_bubble_user) // Use the blue bubble
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.END
                    setMargins(100, 10, 20, 10) // Leave space on the left
                }
            }
            container.addView(textView)
        }


        // 1. FIND THE VIEWS
        val chatMessagesContainer = findViewById<LinearLayout>(R.id.chatMessagesContainer)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val btnSend = findViewById<Button>(R.id.btnSend)

        // 2. GET THE FRIEND'S NAME FROM THE INTENT
        val friendName = intent.getStringExtra("FRIEND_NAME") ?: "Soul Reaper"

        // 3. SHOW THE INITIAL MESSAGE
        addBotMessage("Hello! I am $friendName. How can I help you?", chatMessagesContainer)
        addBotMessage("Hello! I am $friendName. ", chatMessagesContainer)
        addBotMessage("your name please ?", chatMessagesContainer)
        addBotMessage("had lunch today ?", chatMessagesContainer)
        addBotMessage("Anything special today ?", chatMessagesContainer)
        addBotMessage("wow nice to hear that", chatMessagesContainer)
        addBotMessage("HOW IS YOUR DAY ", chatMessagesContainer)
        addBotMessage("are you hungry ?", chatMessagesContainer)
        addBotMessage("OK", chatMessagesContainer)
        addBotMessage("I thing your enjoying your colle?ge days with your friends", chatMessagesContainer)



        // 4. SEND BUTTON LOGIC
        btnSend.setOnClickListener {
            val userText = etMessage.text.toString()
            if (userText.isNotEmpty()) {
                addUserMessage(userText, chatMessagesContainer)
                etMessage.setText("")

                // Make the bot respond using the friend's name
                addBotMessage("$friendName: I am ready for the mission!", chatMessagesContainer)
            }
        }


        // --- PASTE THIS NOW ---
        // 1. Find the Clear Button (Using the Dynamic fix)

        val btnClearChat = findViewById<Button>(resources.getIdentifier("btnClearChat", "id", packageName))

        // 2. Add the click logic
        btnClearChat?.setOnClickListener {
            chatMessagesContainer.removeAllViews()
            Toast.makeText(this, "Messages Wiped", Toast.LENGTH_SHORT).show()
                }
        // -----------------------
    }

    // HELPER FUNCTION: Add Bot Message to Screen
    private fun addBotMessage(message: String, container: LinearLayout) {
        val textView = TextView(this).apply {
            text = message
            setPadding(20, 10, 20, 10)
            setTextColor(Color.BLUE)
            textSize = 16f
        }
        container.addView(textView)
    }

    // HELPER FUNCTION: Add User Message to Screen
    private fun addUserMessage(message: String, container: LinearLayout) {
        val textView = TextView(this).apply {
            text = "Me: $message"
            setPadding(20, 10, 20, 10)
            setTextColor(Color.BLACK)
            textSize = 16f
            gravity = android.view.Gravity.END
        }
        container.addView(textView)
    }
}