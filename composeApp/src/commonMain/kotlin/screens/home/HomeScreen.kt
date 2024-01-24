package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(HomeViewModel::class),
    onNewsClicked: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Text(state, modifier = Modifier.clickable {
        onNewsClicked()
    })
}
