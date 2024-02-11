package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import common_ui.NewsItem
import common_ui.ShowError
import kotlinx.coroutines.launch
import model.NewsModel
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(HomeViewModel::class),
    onNewsClicked: (String, String) -> Unit
) {
    val scaffoldState = rememberScaffoldState() // this contains the `SnackbarHostState`
    Scaffold(scaffoldState = scaffoldState) {
        val state by viewModel.state.collectAsStateWithLifecycle()
        if (state.errorMessage.isNotEmpty()) {
            ShowError(state.errorMessage) {
                viewModel.removeErrorMessage()
            }
        }
        NewsList(
            state.news,
            state.categories,
            state.selectedCategory,
            state.isLoading,
            {
                viewModel.onCategorySelected(it)
            }, onNewsClicked
        )
    }
}

@Composable
fun NewsList(
    newsList: List<NewsModel>,
    categories: List<String>,
    selectedCategory: String,
    isLoading: Boolean,
    onCategoryClicked: (String) -> Unit,
    onNewsClicked: (String, String) -> Unit
) {
    LazyColumn {
        item {
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(categories) { cat ->
                    CategoryItem(cat == selectedCategory, cat) {
                        onCategoryClicked(it)
                    }
                }
            }
        }
        item {
            Spacer(Modifier.height(16.dp))
        }
        if (isLoading) {
            item {
                CenterLoading()
            }
        }
        items(newsList) { newsItem ->
            NewsItem(newsItem) {
                onNewsClicked(newsItem.articleId, newsItem.title)
            }
        }
    }
}


@Composable
fun CategoryItem(
    isSelected: Boolean = false,
    category: String,
    onCategoryClicked: (String) -> Unit
) {

    Card(
        modifier = Modifier.clickable {
            onCategoryClicked(category)
        }, backgroundColor = if (isSelected) Color.Black else Color.White
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            text = category,
            style = MaterialTheme.typography.h6.copy(
                color = if (isSelected) Color.White else Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
    }

}

@Composable
fun CenterLoading() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

}



