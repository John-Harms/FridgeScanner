package com.ahmed_apps.john.aisetup

import android.util.Base64
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

// Retrofit API service interface
interface OpenAIApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun postChatCompletion(@Body request: OpenAIRequest): Call<ResponseBody>
}

// Data classes for request body
data class OpenAIRequest(
    val model: String,
    val messages: List<Message>,
    val max_tokens: Int
)

data class Message(
    val role: String,
    val content: List<Content>
)

data class Content(
    val type: String,
    val text: String? = null,
    val image_url: ImageUrl? = null
)

data class ImageUrl(
    val url: String
)

class OpenAIService {
    // Function to encode image as Base64
    fun encodeImage(imagePath: String): String {
        val imageFile = File(imagePath)
        val imageBytes = imageFile.readBytes()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    // Function to set up Retrofit and make the request
    fun makeApiCall(apiKey: String, base64Image: String, callback: (String?) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        val apiService = retrofit.create(OpenAIApiService::class.java)

        // Prepare the request payload
        val payload = OpenAIRequest(
            model = "gpt-4o-mini",
            messages = listOf(
                Message(
                    role = "user",
                    content = listOf(
                        Content(type = "text", text = "What’s in this image?"),
                        Content(type = "image_url", image_url = ImageUrl(url = "data:image/jpeg;base64,$base64Image"))
                    )
                )
            ),
            max_tokens = 300
        )

        // Make the API request
        val call = apiService.postChatCompletion(payload)

        // Add the Authorization header dynamically
        call.request().newBuilder().addHeader("Authorization", "Bearer $apiKey").build()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback(it.string())
                    } ?: callback(null)
                } else {
                    callback("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback("Request failed: ${t.message}")
            }
        })
    }
}