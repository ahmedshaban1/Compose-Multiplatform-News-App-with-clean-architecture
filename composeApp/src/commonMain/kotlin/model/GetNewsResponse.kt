package model

import kotlinx.serialization.Serializable

@Serializable
data class GetNewsResponse(
    val results: List<NewsModel>
)