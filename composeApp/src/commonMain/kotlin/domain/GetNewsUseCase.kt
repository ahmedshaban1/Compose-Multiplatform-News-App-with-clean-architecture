package domain

import com.ahmed.shaban.remote.Resource
import data.news.repo.NewsRepository
import kotlinx.coroutines.flow.flow
import model.NewsModel
import remote.ResultWrapper

class GetNewsUseCase(private val repository: NewsRepository) {
    operator fun invoke(category: String) = flow {
        when(val results  = repository.getNews(category)){
            is ResultWrapper.GenericError -> emit(Resource.Error(errorCode = results.errorCode))
            is ResultWrapper.Success -> emit(Resource.Success(results.value.results))
        }
    }

}
