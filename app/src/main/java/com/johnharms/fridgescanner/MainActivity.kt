package com.johnharms.fridgescanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.johnharms.fridgescanner.aisetup.OpenAIService
import com.johnharms.fridgescanner.presentation.CameraScreen
import com.johnharms.fridgescanner.theme.CameraXAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //Handles activitytransition animations on newer apis
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handles Button listener and animations for opening mainActivity2
        val textViewRight: TextView = findViewById(R.id.textViewRight)

        textViewRight.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                startActivity(intent)

            } else {
                startActivity(intent)
                overridePendingTransition(
                    R.anim.slide_in_right,  // The animation resource ID for the incoming activity
                    R.anim.slide_out_left   // The animation resource ID for the outgoing activity
                )
            }

        }

        //Checks for permissions on startup
        if (!arePermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this, PERMISSIONS, 100
            )
        }



        val apiKey = "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"


        //Values for imageview and image taken by cameraApi
        val imageView = findViewById<ImageView>(R.id.imageView)

        // Get the Image URI string from the intent
        val imageUriString = intent.getStringExtra("IMAGE_URI")

        //Declares variable used to store converted image taken by user
        var base64Image: String? = null


        // Check if the URI string is not null
        if (imageUriString != null) {
            // Parse the URI string into a Uri object
            val imageUri: Uri = Uri.parse(imageUriString)

            // Create an instance of OpenAIService
            val openAIService = OpenAIService()
            // Use the OpenAIService to encode the image
            base64Image = openAIService.encodeImage(this, imageUri)

        }



        //Logic that displays available photo
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            imageUri?.let {
                val inputStream = contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                imageView.setImageBitmap(bitmap)
            }
        } else {
            imageView.setImageResource(R.drawable.placeholder_image)
        }

        //Code that handles opening camera and managing permissions when button clicked
        val openCameraButton: Button = findViewById(R.id.camerabutton)

        openCameraButton.setOnClickListener {

            if (arePermissionsGranted()) {
                setContent {
                    CameraXAppTheme {
                        CameraScreen(this)
                    }
                }
            } else {
                Toast.makeText(applicationContext, "NO PERMISSIONS!", Toast.LENGTH_SHORT).show()
            }
        }
        //Code To send request to OpenAi API
        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            val openAIService = OpenAIService()
            if (base64Image != null) {

                // Set inProgress to true and launch MainActivity2
                val inProgress = true
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("progress_status", inProgress)
                startActivity(intent)  // Start MainActivity2 here to reflect progress

                // Make API call after MainActivity2 is launched
                openAIService.makeApiCall(apiKey, base64Image) { response ->
                    response?.let {
                        // Extract the message from the API response
                        val message = it.choices.firstOrNull()?.message?.content

                        // You can update MainActivity2 with the message (via Broadcast, LiveData, etc.)
                        val updateIntent = Intent(this, MainActivity2::class.java).apply {
                            putExtra("ai_message", message)
                        }
                        startActivity(updateIntent) // Optionally use a different approach to update UI in MainActivity2
                    } ?: run {
                        println("API call failed.")
                    }
                }
            } else {
                Toast.makeText(this, "No image has been encoded.", Toast.LENGTH_LONG).show()
            }
        }
    }



    //Handles checking for camera and internet permissions
    fun arePermissionsGranted(): Boolean {
        return PERMISSIONS.all { perssion ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                perssion
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        val PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.INTERNET
        )
    }

}






















