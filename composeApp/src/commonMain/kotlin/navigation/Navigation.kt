package navigation

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import navigation.Constants.TITLE
import screens.details.DetailsScreen
import screens.favourite.SearchScreen
import screens.home.HomeScreen

@Composable
fun SetupNavigation(navigator: Navigator) {
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = Screen.Home.route,
    ) {
        scene(
            route = Screen.Home.route,
            navTransition = NavTransition(),
        ) {
            HomeScreen { id, title ->
                navigator.navigate(Screen.Details.getFullRoute(id, title))
            }
        }
        scene(
            route = Screen.Search.route,
            navTransition = NavTransition(),
        ) {
            SearchScreen()
        }

        scene(
            route = Screen.Details.route,
            navTransition = NavTransition(),
        ) {
            val id = it.pathMap[Constants.ID] ?: ""
            val title = it.pathMap[(TITLE)] ?: ""
            DetailsScreen(
                id,
                title
            ) {
                navigator.goBack()
            }
        }
    }
}
