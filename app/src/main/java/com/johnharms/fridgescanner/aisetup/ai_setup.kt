package com.johnharms.fridgescanner.aisetup

import android.content.Context
import android.net.Uri
import android.util.Base64
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// Retrofit API service interface
interface OpenAIApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    fun postChatCompletion(@Body request: OpenAIRequest): Call<OpenAIResponse>
}

// Data classes for request body
data class OpenAIRequest(
    val model: String,
    val messages: List<Message>,
    val max_tokens: Int,
    val temperature: Double? = null,          // New optional field
    val top_p: Double? = null,                // New optional field
    val frequency_penalty: Double? = null,    // New optional field
    val presence_penalty: Double? = null      // New optional field
)

// Data class for the API response
data class OpenAIResponse(
    @SerializedName("choices") val choices: List<Choice>
)

data class Choice(
    @SerializedName("message") val message: MessageResponse
)

data class MessageResponse(
    @SerializedName("role") val role: String,
    @SerializedName("content") val content: String
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
    fun encodeImage(context: Context, imageUri: Uri): String? {
        try {
            // Use content resolver to open the InputStream for the URI
            val inputStream = context.contentResolver.openInputStream(imageUri)

            // Convert the InputStream into bytes
            val imageBytes = inputStream?.readBytes()

            // Close the InputStream
            inputStream?.close()

            // Return the Base64 encoded string
            return imageBytes?.let { Base64.encodeToString(it, Base64.DEFAULT) }

        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    // Function to set up Retrofit and make the request
    fun makeApiCall(apiKey: String, base64Image: String, callback: (OpenAIResponse?) -> Unit) {

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $apiKey")
                .build()
            chain.proceed(request)
        }.build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openai.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val apiService = retrofit.create(OpenAIApiService::class.java)

        // Prepare the request payload
        val payload = OpenAIRequest(
            model = "gpt-4o-mini",
            messages = listOf(

                Message(
                    role = "system",
                    content = listOf(
                        Content(
                            type = "text",
                            text = """
                        1. From the attached picture identify the Type and Quantity of each food present.
                        2. Don't count the individual items in a package.
                        3. Enter only one number for Quantity.
                        4. Enter only one word for Type.
                        5. Capitalize first letter of any word.
                        6. Format for response is "(Type, Quantity)(Type, Quantity), ETC).
                        7. If no food is present in the picture, return 0.
                    """.trimIndent()
                        )
                    )
                ),

                Message(
                    role = "user",
                    content = listOf(
                        Content(type = "image_url", image_url = ImageUrl(url = "data:image/jpeg;base64,$base64Image"))
                    )
                )
            ),
            max_tokens = 300,
            temperature  = 0.0,           // Add temperature parameter
            top_p = 1.0,                 // Add top_p parameter
            frequency_penalty = 0.0,     // Add frequency_penalty parameter
            presence_penalty = 0.0       // Add presence_penalty parameter
        )

        // Make the API request
        val call = apiService.postChatCompletion(payload)

        // Add the Authorization header dynamically
        call.request().newBuilder().addHeader("Authorization", "Bearer $apiKey").build()

        call.enqueue(object : Callback<OpenAIResponse> {
            override fun onResponse(call: Call<OpenAIResponse>, response: Response<OpenAIResponse>) {
                if (response.isSuccessful) {
                    // Store the response in an object and pass it through the callback
                    val openAIResponse = response.body()
                    callback(openAIResponse)
                } else {
                    callback(null)  // Handle error case
                }
            }

            override fun onFailure(call: Call<OpenAIResponse>, t: Throwable) {
                callback(null)  // Handle failure case
            }
        })
    }
}
