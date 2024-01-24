package screens.details

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetailsScreen(onNavigateUp: () -> Unit) {
    Text("Hello i'm Details" , modifier = Modifier.clickable {
        onNavigateUp()
    })
}
