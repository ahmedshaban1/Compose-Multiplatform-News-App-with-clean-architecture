package screens.details

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun DetailsScreen(
    id: String,
    title: String,
    viewModel: DetailsViewModel = koinViewModel(DetailsViewModel::class),
    onNavigateUp: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(true){
        viewModel.getSingleNews(id,title)
    }
    state.singleNews?.let {
        Text(it.title, modifier = Modifier.clickable {

        })
    }

}
