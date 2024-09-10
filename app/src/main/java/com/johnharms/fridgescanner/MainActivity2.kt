package com.johnharms.fridgescanner

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity



class MainActivity2 : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN,
                R.anim.slide_in_right,
                R.anim.slide_out_left,
            )
        }



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        // Get the TextView by its ID
        val responseTextView: TextView = findViewById(R.id.responseTextView)



        val inProgress = intent.getBooleanExtra("progress_status", false)

        if (inProgress) {
            // If still in progress, show "Processing" message
            responseTextView.text = "Processing image! Please wait."
        } else {
            responseTextView.text = "No image processsing."
        }

        // Get the AI message from the intent if available
        val message = intent.getStringExtra("ai_message")
        message?.let {
            // Update the responseTextView with the formatted message when it's available
            val formattedString = formatString(it)
            responseTextView.text = formattedString
        }

        val textView2: TextView = findViewById(R.id.textViewLeft)

        textView2.setOnClickListener {

            val intent2 = Intent(this, MainActivity::class.java)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startActivity(intent2)
            } else {
                startActivity(intent2)
                overridePendingTransition(
                    R.anim.slide_in_left,  // The animation resource ID for the incoming activity
                    R.anim.slide_out_right   // The animation resource ID for the outgoing activity
                )
            }


        }

    }

    // Function to format the string
    private fun formatString(input: String): String {
        // Use regex to find all instances of (Type, Quantity)
        val regex = "\\(([^)]+)\\)".toRegex()

        // Extract each match and format it
        val matches = regex.findAll(input).map { matchResult ->
            val group = matchResult.groupValues[1] // Extracts the content inside the parenthesis
            "• $group" // Add a bullet point to the start
        }

        // Join the formatted lines with a newline character
        return matches.joinToString("\n")
    }

}

//End
