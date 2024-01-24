package screens.home

import domain.GetNewsUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel

class HomeViewModel(private val getNewsUsecase: GetNewsUsecase) : ViewModel() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()
    init {
        _state.update {
            getNewsUsecase.getNews()
        }
    }
}
