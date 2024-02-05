package data.news.repo

import data.news.data_source.NewsRemoteDataSource
import model.GetNewsResponse
import model.NewsModel
import remote.ResultWrapper

interface NewsRepository {
    suspend fun getNews(category: String): ResultWrapper<GetNewsResponse>
    suspend fun getNewsCategories(): List<String>
    suspend fun getSingleNews(title: String): List<NewsModel>

}

class NewsRepositoryImpl(private val remoteDataSource: NewsRemoteDataSource) :
    NewsRepository {
    override suspend fun getNews(category: String) = remoteDataSource.getNews(category)

    override suspend fun getNewsCategories() = listOf(
        "all",
        "Business",
        "Cars",
        "Entertainment",
        "Family",
        "Health",
        "Politics",
        "Religion",
        "Science",
        "Sports",
        "Technology",
        "Travel",
        "Video",
        "World"
    )

    override suspend fun getSingleNews(title: String): List<NewsModel> {
        return remoteDataSource.getSingleNews(title).results
    }

}

