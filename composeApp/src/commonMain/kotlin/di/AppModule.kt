package di

import org.koin.dsl.module
import screens.home.HomeViewModel

val appModule = module {
    factory {
        HomeViewModel()
    }
}
