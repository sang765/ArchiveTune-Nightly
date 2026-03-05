#!/usr/bin/env kotlin

@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("com.google.code.gson:gson:2.13.2")

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.exitProcess

// --- Global Instances ---
val gson = Gson()
val client = HttpClient.newHttpClient()

// --- Helper Functions ---

fun fetch(url: String, method: String = "GET", body: String? = null): String {
    val builder = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Content-Type", "application/json")
    
    if (method == "POST") {
        builder.POST(HttpRequest.BodyPublishers.ofString(body ?: ""))
    }
    
    val response = client.send(builder.build(), HttpResponse.BodyHandlers.ofString())
    if (response.statusCode() !in 200..299) {
        throw RuntimeException("Request failed: $url - Status: ${response.statusCode()} - Body: ${response.body()}")
    }
    return response.body()
}

fun main() {
    try {
        // 1. Get environment variables
        val botToken = System.getenv("TELEGRAM_BOT_TOKEN")
            ?: throw IllegalArgumentException("TELEGRAM_BOT_TOKEN not set")
        val chatId = System.getenv("TELEGRAM_CHAT_ID")
            ?: throw IllegalArgumentException("TELEGRAM_CHAT_ID not set")
        val threadId = System.getenv("TELEGRAM_THREAD_ID")?.takeIf { it.isNotBlank() }
        val releaseTag = System.getenv("RELEASE_TAG")
            ?: throw IllegalArgumentException("RELEASE_TAG not set")
        val repository = System.getenv("GITHUB_REPOSITORY")
            ?: "sang765/ArchiveTune-Nightly"
        
        println("=== Telegram Notification ===")
        println("Release: $releaseTag")
        println("Chat ID: $chatId")
        println("Thread ID: $threadId")
        
        val releaseUrl = "https://github.com/$repository/releases/tag/$releaseTag"
        
        // 2. Build the message (plain text, no markdown)
        val apkLinks = listOf(
            "Mobile 64-bit (arm64)" to "app-arm64-nightly.apk",
            "Mobile 32-bit (armeabi)" to "app-armeabi-nightly.apk",
            "Tablet 32-bit (x86)" to "app-x86-nightly.apk",
            "Tablet 64-bit (x86_64)" to "app-x86_64-nightly.apk",
            "Universal" to "ArchiveTune-Nightly.apk"
        )
        
        val downloadSection = apkLinks.joinToString("\n") { (name, file) ->
            val url = "https://github.com/$repository/releases/download/$releaseTag/$file"
            "- $name: $url"
        }
        
        val message = "ArchiveTune Nightly $releaseTag Released\n\nCheck out changelog here: $releaseUrl\n\nDownload:\n$downloadSection"
        
        // 3. Build JSON payload (no markdown)
        val json = JsonObject().apply {
            addProperty("chat_id", chatId)
            addProperty("text", message)
            threadId?.let { addProperty("message_thread_id", it) }
        }
        
        // 4. Send to Telegram
        val url = "https://api.telegram.org/bot$botToken/sendMessage"
        val response = fetch(url, "POST", gson.toJson(json))
        
        println("Response: $response")
        
        // Parse response
        val responseObj = gson.fromJson(response, JsonObject::class.java)
        if (responseObj.get("ok").asBoolean) {
            println("Message sent successfully!")
        } else {
            val error = responseObj.get("description")?.asString ?: "Unknown error"
            println("Error: $error")
            exitProcess(1)
        }
        
    } catch (e: Exception) {
        System.err.println("Error: ${e.message}")
        e.printStackTrace()
        exitProcess(1)
    }
}

// Run the main function
main()
