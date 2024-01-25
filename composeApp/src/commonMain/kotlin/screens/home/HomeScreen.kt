package screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import common_ui.NewsItem
import model.NewsModel
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(HomeViewModel::class),
    onNewsClicked: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NewsList(state.news, onNewsClicked)

}

@Composable
fun NewsList(newsList: List<NewsModel>, onNewsClicked: (String) -> Unit) {
    AnimatedVisibility(
        newsList.isNotEmpty(),
        enter = fadeIn() + expandVertically()
    ) {
        LazyColumn {
            items(newsList) { newsItem ->
                NewsItem(newsItem) {
                    onNewsClicked(newsItem.articleId)
                }
            }
        }
    }
}
