package data.news.repo

import data.news.data_source.NewsRemoteDataSource
import model.NewsModel

interface NewsRepository {
    suspend fun getNews(): List<NewsModel>
}

class NewsRepositoryImpl(private val remoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getNews() =
        remoteDataSource.getNews().results

}
