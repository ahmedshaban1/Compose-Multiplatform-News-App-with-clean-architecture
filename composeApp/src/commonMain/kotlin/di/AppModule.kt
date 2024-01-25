package di

import data.news.data_source.NewsRemoteDataSource
import data.news.data_source.NewsRemoteDataSourceImpl
import data.news.repo.NewsRepository
import data.news.repo.NewsRepositoryImpl
import domain.GetNewsUseCase
import org.koin.dsl.module
import screens.home.HomeViewModel

val appModule = module {
    factory {
        GetNewsUseCase(get())
    }
    factory {
        HomeViewModel(get())
    }
    single<NewsRemoteDataSource> {
        NewsRemoteDataSourceImpl(get())
    }
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
}