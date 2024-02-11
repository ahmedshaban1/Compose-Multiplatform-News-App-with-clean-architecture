package screens.details

import com.ahmed.shaban.remote.Resource
import domain.GetSingleNewsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import model.NewsModel
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class State(
    val singleNews: NewsModel? = null,
    val isLoading: Boolean = false,
    val message: String = ""
)

class DetailsViewModel(
    private val getSingleNewsUseCase: GetSingleNewsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun getSingleNews(id: String, title: String) {
        viewModelScope.launch {
            _state.update {
                State(isLoading = true)
            }
            getSingleNewsUseCase(title, id).collectLatest { results ->
                when (results) {
                    is Resource.Error -> {
                        _state.update {
                            State(message = "Something went wrong, please try again")
                        }
                    }

                    is Resource.Success -> {
                        _state.update {
                            State(results.data)
                        }
                    }
                }
            }
        }
    }

    fun removeErrorMessage() {
        _state.update {
            State()
        }
    }
}
