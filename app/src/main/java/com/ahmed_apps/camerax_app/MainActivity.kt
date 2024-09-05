package com.ahmed_apps.camerax_app

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ahmed_apps.camerax_app.presentation.CameraScreen
import com.ahmed_apps.camerax_app.ui.theme.CameraXAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageUriString = intent.getStringExtra("IMAGE_URI")

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





        val openCameraButton: Button = findViewById(R.id.camerabutton)

        openCameraButton.setOnClickListener {

            if (!arePermissionsGranted()) {
                ActivityCompat.requestPermissions(
                    this, CAMERA_PERMISSION, 100
                )
            }

            setContent {
                CameraXAppTheme {
                    CameraScreen(this)
                }
            }
        }
    }

    fun arePermissionsGranted(): Boolean {
        return CAMERA_PERMISSION.all { perssion ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                perssion
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        val CAMERA_PERMISSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
        )
    }

}






















