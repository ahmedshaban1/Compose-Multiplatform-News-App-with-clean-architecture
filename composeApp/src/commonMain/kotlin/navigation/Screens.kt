package navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Home : Screen("/home")
    data object Favourite : Screen("/favourite")
    data object Details : Screen("/details")
}

data class BottomNavigationModel(
    val screen: Screen, val title: String, val icon: ImageVector
)

val bottomNavigationScreens = listOf(
    BottomNavigationModel(
        screen = Screen.Home,
        title = "Home",
        icon = Icons.Default.Home
    ), BottomNavigationModel(
        screen = Screen.Favourite,
        title = "Favourite",
        icon = Icons.Default.Favorite
    )
)