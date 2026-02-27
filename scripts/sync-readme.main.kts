#!/usr/bin/env kotlin

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.exitProcess

// --- Global Instances ---
val client = HttpClient.newHttpClient()

// --- Helper Functions ---

fun fetchContent(url: String): String? {
    return try {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36")
            .GET()
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        if (response.statusCode() !in 200..299) {
            println("Loading error $url: Status ${response.statusCode()}")
            return null
        }
        response.body()
    } catch (e: Exception) {
        println("Loading error $url: ${e.message}")
        null
    }
}

fun main() {
    try {
        // Step 1: Copy temp/README.md to README.md
        val tempReadme = File("temp/README.md")
        val readme = File("README.md")
        
        if (!tempReadme.exists()) {
            System.err.println("Error: temp/README.md not found")
            exitProcess(1)
        }
        
        tempReadme.copyTo(readme, overwrite = true)
        println("Copied temp/README.md to README.md")
        
        // Step 2: Define sync map
        val syncMap = mapOf(
            "Sync README.md content from https://github.com/koiverse/ArchiveTune raw." to 
                "https://raw.githubusercontent.com/koiverse/ArchiveTune/main/README.md",
            
            "Sync CONTRIBUTING.md content from https://github.com/koiverse/ArchiveTune raw." to 
                "https://raw.githubusercontent.com/koiverse/ArchiveTune/dev/CONTRIBUTING.md"
        )
        
        // Step 3: Read current README.md content
        var content = readme.readText(Charsets.UTF_8)
        
        // Step 4: Fetch and replace content for each placeholder
        for ((placeholder, url) in syncMap) {
            println("Synchronizing from: $url...")
            val rawData = fetchContent(url)
            
            if (rawData != null) {
                content = content.replace(placeholder, rawData)
            } else {
                println("Placeholder ignored because data could not be loaded.")
            }
        }
        
        // Step 5: Write the updated content back to README.md
        readme.writeText(content, Charsets.UTF_8)
        
        println("Synchronization complete!")
        
    } catch (e: Exception) {
        System.err.println("Error: ${e.message}")
        e.printStackTrace()
        exitProcess(1)
    }
}

// Run the main function
main()
