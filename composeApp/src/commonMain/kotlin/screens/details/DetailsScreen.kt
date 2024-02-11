package screens.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import common_ui.ShowError
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import screens.home.CenterLoading

@Composable
fun DetailsScreen(
    id: String,
    title: String,
    viewModel: DetailsViewModel = koinViewModel(DetailsViewModel::class),
    onNavigateUp: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        viewModel.getSingleNews(id, title)
    }
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(
            state = scroll,
        )
    ) {
        if (state.isLoading){
            CenterLoading()
        }
        if (state.message.isNotEmpty()){
            ShowError(state.message){
                viewModel.removeErrorMessage()
                onNavigateUp()
            }
        }
        state.singleNews?.let { newsModel ->
            KamelImage(
                asyncPainterResource(newsModel.imageUrl ?: ""),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                newsModel.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(16.dp),
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Creator : ${newsModel.getCreator()}",
                    style = MaterialTheme.typography.subtitle1,
                )
                Text(
                    newsModel.pubDate,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                newsModel.generateDummyContent(),
                style = MaterialTheme.typography.body1,
            )
        }
    }


}
