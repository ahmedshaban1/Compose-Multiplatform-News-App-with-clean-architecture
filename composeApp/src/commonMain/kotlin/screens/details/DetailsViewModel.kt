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

data class State(val singleNews: NewsModel? = null)
class DetailsViewModel(
    private val getSingleNewsUseCase: GetSingleNewsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    fun getSingleNews(id: String, title: String) {
        viewModelScope.launch {
            getSingleNewsUseCase(title, id).collectLatest {results->
                when(results){
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        _state.update {
                            State(results.data)
                        }
                    }
                }
            }
        }
    }
}
