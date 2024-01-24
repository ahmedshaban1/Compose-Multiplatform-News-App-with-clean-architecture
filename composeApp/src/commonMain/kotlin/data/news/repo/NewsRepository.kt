package data.news.repo

import data.news.data_source.NewsRemoteDataSource

interface NewsRepository {
}

class NewsRepositoryImpl(private val remoteDataSource: NewsRemoteDataSource) : NewsRepository {

}
