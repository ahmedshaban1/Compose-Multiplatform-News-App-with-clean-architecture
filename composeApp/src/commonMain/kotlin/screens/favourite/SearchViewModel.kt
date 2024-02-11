package screens.favourite

import com.ahmed.shaban.remote.Resource
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
    val searchResults: List<NewsModel> = listOf(),
    val queryString: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)

class SearchViewModel(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private fun search() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    searchResults = listOf(),
                    isLoading = true
                )
            }
            getNewsUseCase(_state.value.queryString).collectLatest { response ->
                when (response) {
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                errorMessage = "Something went wrong, please try again",
                                isLoading = false
                            )
                        }
                    }
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                searchResults = response.data ?: listOf(),
                                isLoading = false
                            )
                        }
                    }
                }
            }

        }
    }

    fun updateQuery(newQuery: String) {
        _state.update {
            State(queryString = newQuery)
        }
        if (newQuery.length >= 3)
            search()
    }

    fun removeErrorMessage(){
        _state.update {
            it.copy(
                errorMessage = ""
            )
        }
    }
}
