import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import navigation.Screen
import navigation.SetupNavigation
import navigation.bottomNavigationScreens
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinContext
import theme.ComposeKMPTheme

@Composable
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        var currentRoute: String by remember {
            mutableStateOf("")
        }
        KoinContext {
            ComposeKMPTheme {
                LaunchedEffect(true) {
                    navigator.currentEntry.collect {
                        currentRoute = it?.route?.route ?: ""
                    }
                }

                Scaffold(
                    modifier = Modifier.padding(16.dp),
                    bottomBar = {
                        val isBottomNavigationItem =
                            bottomNavigationScreens.findLast { it.screen.route == currentRoute }
                        AnimatedVisibility(
                            visible = isBottomNavigationItem != null,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            CreateAppBottomBar(currentRoute) {
                                navigator.navigate(
                                    route = it, NavOptions(
                                        launchSingleTop = true,
                                    )
                                )
                            }
                        }

                    }) {
                    SetupNavigation(navigator)
                }

            }
        }
    }
}

@Composable
fun CreateAppBottomBar(
    currentRoute: String,
    onItemSelected: (String) -> Unit
) {
    BottomNavigation(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(androidx.compose.foundation.shape.CircleShape),
        backgroundColor = Color.White,
    ) {
        bottomNavigationScreens.forEach { item ->
            val selected = currentRoute == item.screen.route
            BottomNavigationItem(selected = selected,
                label = { Text(item.title) },
                onClick = { onItemSelected(item.screen.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = if (selected) Color.Black else
                            Color.Gray
                    )
                })
        }
    }
}
