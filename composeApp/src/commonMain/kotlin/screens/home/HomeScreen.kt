package screens.home

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(onNewsClicked: () -> Unit) {
    Text("Hello i'm home", modifier = Modifier.clickable {
        onNewsClicked()
    })
}
