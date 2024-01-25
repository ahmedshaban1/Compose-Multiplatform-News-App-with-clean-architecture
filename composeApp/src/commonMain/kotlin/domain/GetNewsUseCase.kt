package domain

import data.news.repo.NewsRepository
import model.NewsModel

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend fun getNews(): List<NewsModel> = repository.getNews()

}
