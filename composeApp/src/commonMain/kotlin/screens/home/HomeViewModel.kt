package screens.home

import domain.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.NewsModel
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class State(
    val news: List<NewsModel> = listOf()
)

class HomeViewModel(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                State(getNewsUseCase.getNews())
            }
        }
    }
}
