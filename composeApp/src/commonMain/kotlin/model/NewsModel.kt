package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsModel(
    @SerialName("ai_region")
    val aiRegion: String,
    @SerialName("ai_tag")
    val aiTag: String,
    @SerialName("article_id")
    val articleId: String,
    @SerialName("category")
    val category: List<String>,
    @SerialName("content")
    val content: String,
    @SerialName("country")
    val country: List<String>,
    @SerialName("creator")
    val creator: List<String>?,
    @SerialName("description")
    val description: String?,
    @SerialName("image_url")
    val imageUrl: String?,
    @SerialName("language")
    val language: String,
    @SerialName("link")
    val link: String,
    @SerialName("pubDate")
    val pubDate: String,
    @SerialName("sentiment")
    val sentiment: String,
    @SerialName("sentiment_stats")
    val sentimentStats: String,
    @SerialName("source_id")
    val sourceId: String,
    @SerialName("source_priority")
    val sourcePriority: Int,
    @SerialName("source_url")
    val sourceUrl: String,
    @SerialName("title")
    val title: String
) {
    fun getCreator(): String {
        return creator?.joinToString(",") ?: "N/A"
    }

    fun generateDummyContent(): String {
        val text = StringBuilder()
        repeat(50){
            text.append(content)
            text.append(" ")
            text.append(content)
            text.append("\n")
        }
        return text.toString()
    }
}