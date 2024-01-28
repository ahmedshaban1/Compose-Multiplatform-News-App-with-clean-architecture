package domain

import data.news.repo.NewsRepository

class GetCategoriesUseCase(private val repository: NewsRepository) {
    suspend operator fun invoke(): List<String> = repository.getNewsCategories()

}
