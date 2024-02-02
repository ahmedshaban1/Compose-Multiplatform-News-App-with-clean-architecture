package screens.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common_ui.NewsItem
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import screens.home.NewsList

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(SearchViewModel::class),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = state.queryString,
                placeholder = { Text("Search here") },
                onValueChange = {
                    viewModel.updateQuery(it)
                })
            Spacer(Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (state.searchResults.isNotEmpty()){
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(state.searchResults) { newsItem ->
                            NewsItem(newsItem) {
                                //onNewsClicked(newsItem.articleId, newsItem.title)
                            }
                        }
                    }
                }
            }
        }
    }
}