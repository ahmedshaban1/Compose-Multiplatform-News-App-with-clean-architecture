import androidx.compose.ui.window.ComposeUIViewController
import di.appModule
import di.networkModule
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    startKoin {
        modules(appModule, networkModule)
    }
}
