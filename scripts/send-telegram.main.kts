#!/usr/bin/env kotlin

@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("com.google.code.gson:gson:2.10.1")

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.exitProcess

// --- Configuration ---
data class Config(
    val botToken: String,
    val chatId: String,
    val threadId: String?,
    val releaseTag: String,
    val repository: String
)

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

fun escapeMarkdownV2(text: String): String {
    // Characters that need escaping in MarkdownV2: \ _ * [ ] ( ) ~ ` > # + - = | { } . !
    val specialChars = listOf("\\", "_", "*", "[", "]", "(", ")", "~", "`", ">", "#", "+", "-", "=", "|", "{", "}", ".", "!")
    var escaped = text
    for (char in specialChars) {
        escaped = escaped.replace(char, "\\$char")
    }
    return escaped
}

fun formatSize(bytes: Long): String {
    return when {
        bytes >= 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024 * 1024)}GB"
        bytes >= 1024 * 1024 -> "${bytes / (1024 * 1024)}MB"
        bytes >= 1024 -> "${bytes / 1024}KB"
        else -> "$bytes B"
    }
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
        
        // 2. Build the message
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
        
        val message = """
            *ArchiveTune Nightly $releaseTag Released*
            
            Check out changelog here: $releaseUrl
            
            *Download:*
            $downloadSection
        """.trimIndent()
        
        // 3. Escape for MarkdownV2
        val escapedMessage = escapeMarkdownV2(message)
        
        // 4. Build JSON payload
        val json = JsonObject().apply {
            addProperty("chat_id", chatId)
            addProperty("text", escapedMessage)
            addProperty("parse_mode", "MarkdownV2")
            threadId?.let { addProperty("message_thread_id", it) }
        }
        
        // 5. Send to Telegram
        val url = "https://api.telegram.org/bot$botToken/sendMessage"
        val response = fetch(url, "POST", gson.toJson(json))
        
        println("Response: $response")
        
        // Parse response
        val responseObj = gson.fromJson(response, JsonObject::class.javaresponseObj.get(")
        if (ok").asBoolean) {
            println("✅ Message sent successfully!")
        } else {
            val error = responseObj.get("description")?.asString ?: "Unknown error"
            println("❌ Error: $error")
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
