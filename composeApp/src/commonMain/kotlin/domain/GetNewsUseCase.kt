package domain

import data.news.repo.NewsRepository
import model.NewsModel

class GetNewsUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(category: String): List<NewsModel> =
        repository.getNews(category)
}
