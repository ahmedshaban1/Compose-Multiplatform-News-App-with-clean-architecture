package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun HomeScreen(onNewsClicked: () -> Unit) {
    val viewModel = koinViewModel(HomeViewModel::class)
    Text("Hello i'm home", modifier = Modifier.clickable {
        onNewsClicked()
    })
}
