package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import navigation.Constants.ID
import navigation.Constants.TITLE

sealed class Screen(val route: String) {
    data object Home : Screen("/home")
    data object Search : Screen("/search")
    data object Details :
        Screen("/details/{$ID}/{$TITLE}") {
        fun getFullRoute(id: String, title: String): String {
            return "/details/$id/$title"
        }
    }
}

object Constants {
    const val ID = "id"
    const val TITLE = "title"
}

data class BottomNavigationModel(
    val screen: Screen,
    val title: String,
    val icon: ImageVector,
)

val bottomNavigationScreens = listOf(
    BottomNavigationModel(
        screen = Screen.Home,
        title = "Home",
        icon = Icons.Default.Home,
    ), BottomNavigationModel(
        screen = Screen.Search,
        title = "Search",
        icon = Icons.Default.Search
    )
)
