package common_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.NewsModel

@Composable
fun NewsItem(newsModel: NewsModel, onItemClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().clickable {
        onItemClicked()
    }.height(170.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            KamelImage(asyncPainterResource(
                newsModel.imageUrl ?: "",
            ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(137.dp, 140.dp).background(color = Color.Gray),
                onLoading = { progress -> CircularProgressIndicator(progress) })
            Column(
                modifier = Modifier.padding(horizontal = 8.dp).height(140.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    newsModel.title,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = newsModel.getCreator(),
                    style = MaterialTheme.typography.subtitle1.copy(color = Color.Gray)
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        Spacer(
            modifier = Modifier.background(Color.Gray)
                .fillMaxWidth()
                .height(1.dp)
        )
        Spacer(Modifier.height(8.dp))
    }

}
