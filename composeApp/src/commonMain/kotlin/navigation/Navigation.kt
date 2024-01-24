package navigation

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import screens.details.DetailsScreen
import screens.favourite.FavouriteScreen
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
            HomeScreen {
                navigator.navigate(Screen.Details.route)
            }
        }
        scene(
            route = Screen.Favourite.route,
            navTransition = NavTransition(),
        ) {
            FavouriteScreen()
        }

        scene(
            route = Screen.Details.route,
            navTransition = NavTransition(),
        ) {
            DetailsScreen{
                navigator.goBack()
            }
        }
    }
}