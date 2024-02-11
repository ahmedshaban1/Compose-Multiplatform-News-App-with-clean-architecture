package screens.home

import com.ahmed.shaban.remote.Resource
import domain.GetCategoriesUseCase
import domain.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.NewsModel
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class State(
    val news: List<NewsModel> = listOf(),
    val categories: List<String> = listOf(),
    val selectedCategory: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

class HomeViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val cats = getCategoriesUseCase()
            _state.update {
                it.copy(
                    categories = cats
                )
            }
            getNews(cats.first())
        }
    }

    private suspend fun getNews(category: String) {
        showLoading()
        getNewsUseCase(category).collectLatest { response ->
            when (response) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            errorMessage = "Something went wrong , please try again",
                            isLoading = false
                        )
                    }
                }

                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            news = response.data ?: listOf(),
                            selectedCategory = category,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun onCategorySelected(selectedCategory: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCategory = selectedCategory,
                    news = listOf()
                )
            }
            getNews(selectedCategory)
        }
    }

    private fun showLoading() {
        _state.update {
            it.copy(isLoading = true)
        }
    }

    fun removeErrorMessage() {
        _state.update {
            it.copy(errorMessage = "")
        }
    }
}
