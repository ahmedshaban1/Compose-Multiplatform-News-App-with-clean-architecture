package screens.favourite

import domain.GetNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.NewsModel
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class State(
    val searchResults: List<NewsModel> = listOf(),
    val queryString: String = ""
)

class SearchViewModel(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

   private fun search() {
        viewModelScope.launch {
            _state.update {
                it.copy(searchResults = listOf())
            }
            val results = getNewsUseCase(_state.value.queryString)
            _state.update {
               it.copy(searchResults = results)
            }
        }
    }

    fun updateQuery(newQuery: String) {
        _state.update {
            State(queryString = newQuery)
        }
        search()
    }
}
