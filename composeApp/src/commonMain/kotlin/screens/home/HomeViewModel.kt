package screens.home

import domain.GetCategoriesUseCase
import domain.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.NewsModel
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class State(
    val news: List<NewsModel> = listOf(),
    val categories: List<String> = listOf(),
    val selectedCategory: String = ""
)

class HomeViewModel(
    private val getNewsUseCase: GetNewsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val cats = getCategoriesUseCase()
            _state.update {
                State(
                    getNewsUseCase(cats[0]),
                    selectedCategory = cats[0],
                    categories = cats
                )
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
            _state.update {
                it.copy(
                    news = getNewsUseCase(selectedCategory)
                )
            }
        }
    }
}
