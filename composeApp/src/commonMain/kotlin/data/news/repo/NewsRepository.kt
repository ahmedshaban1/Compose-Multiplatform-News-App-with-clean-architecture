package data.news.repo

import data.news.data_source.NewsRemoteDataSource
import model.GetNewsResponse
import model.NewsModel

interface NewsRepository {
    suspend fun getNews(category: String): List<NewsModel>
    suspend fun getNewsCategories(): List<String>
    suspend fun getSingleNews(title: String): List<NewsModel>

}

class NewsRepositoryImpl(private val remoteDataSource: NewsRemoteDataSource) :
    NewsRepository {
    override suspend fun getNews(category: String) = remoteDataSource.getNews(category).results

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

