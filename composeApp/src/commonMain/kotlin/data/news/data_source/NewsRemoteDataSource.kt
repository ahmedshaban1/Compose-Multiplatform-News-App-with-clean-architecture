package data.news.data_source

import model.NewsModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import model.GetNewsResponse

interface NewsRemoteDataSource {
    suspend fun getNews(): GetNewsResponse
}

class NewsRemoteDataSourceImpl(private val httpClient: HttpClient) : NewsRemoteDataSource {
    override suspend fun getNews(): GetNewsResponse {
        return httpClient.get("https://newsdata.io/api/1/news?apikey=pub_370578d1b57f141329a1939e5481441ccfcd2&q=pizza")
            .body<GetNewsResponse>()
    }

}
