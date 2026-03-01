#!/usr/bin/env kotlin

@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("com.google.code.gson:gson:2.10.1")

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonArray
import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

val gson = Gson()
val client = HttpClient.newHttpClient()

fun getLastBuildTime(repo: String): String {
    val url = "https://api.github.com/repos/$repo/actions/runs?per_page=10"
    val request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36")
        .GET()
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    if (response.statusCode() !in 200..299) {
        throw Exception("Failed to fetch: ${response.statusCode()}")
    }

    val data = gson.fromJson(response.body(), JsonObject::class.java)
    val runs = data.getAsJsonArray("workflow_runs")

    val nightlyRun = runs.find { run ->
        run.asJsonObject.get("name").asString == "Nightly Build" && 
        run.asJsonObject.get("conclusion").asString == "success"
    } ?: throw Exception("No successful Nightly Build run found")

    val createdAt = nightlyRun.asJsonObject.get("created_at").asString
    val date = ZonedDateTime.parse(createdAt)
    val utcDate = date.withZoneSameInstant(java.time.ZoneOffset.UTC)
    val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
    return utcDate.format(formatter)
}

fun generateBadge() {
    try {
        val repo = System.getenv("GITHUB_REPOSITORY") ?: "sang765/ArchiveTune-Nightly"
        val buildTime = getLastBuildTime(repo)
        val text = "Build: $buildTime"

        val iconWidth = 40
        val gap = 8
        val groupPadding = 12
        val textWidth = text.length * 15
        val groupWidth = iconWidth + gap + textWidth
        val height = groupPadding * 2 + 40
        val width = groupPadding * 2 + groupWidth

        val svg = """
            <svg width="$width" height="$height" xmlns="http://www.w3.org/2000/svg">
              <rect x="0" y="0" width="$width" height="$height" rx="30" ry="30" fill="#A5D6A7"/>
              <g transform="translate($groupPadding, $groupPadding)">
                <circle cx="20" cy="20" r="20" fill="none" stroke="#2E7D32" stroke-width="3"/>
                <path d="M20 6.67v13.33l5 3" stroke="#2E7D32" stroke-width="3" stroke-linecap="round" stroke-linejoin="round" fill="none"/>
                <text x="${iconWidth + gap}" y="30" font-family="Roboto" font-size="30" font-weight="900" fill="#2E7D32">$text</text>
              </g>
            </svg>
        """.trimIndent()

        val outputPath = File("images", "badges").resolve("last-nightly-build.svg")
        outputPath.parentFile.mkdirs()
        outputPath.writeText(svg)
        println("Badge generated at ${outputPath.absolutePath}")
    } catch (error: Exception) {
        println("Error generating badge: ${error.message}")
        error.printStackTrace()
        exitProcess(1)
    }
}

generateBadge()
