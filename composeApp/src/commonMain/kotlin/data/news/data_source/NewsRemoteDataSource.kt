package data.news.data_source

import constants.NetworkConstants.API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import model.GetNewsResponse

interface NewsRemoteDataSource {
    suspend fun getNews(category: String): GetNewsResponse
    suspend fun getSingleNews(title: String): GetNewsResponse
}

class NewsRemoteDataSourceImpl(private val httpClient: HttpClient) :
    NewsRemoteDataSource {
    override suspend fun getNews(category: String): GetNewsResponse {
        return httpClient.get("https://newsdata.io/api/1/news?apikey=$API_KEY&q=$category")
            .body<GetNewsResponse>()
    }

    override suspend fun getSingleNews(title: String): GetNewsResponse {
        return httpClient.get("https://newsdata.io/api/1/news?apikey=$API_KEY&qInTitle=\"$title\"")
            .body<GetNewsResponse>()
    }

}
