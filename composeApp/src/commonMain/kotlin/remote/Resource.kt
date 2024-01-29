package com.ahmed.shaban.remote

sealed class Resource<out T> {
    class Error(val errorCode: Int) : Resource<Nothing>()
    class Success<out T>(val data: T?, val dataSource: DataSource = DataSource.REMOTE) : Resource<T>()
}

enum class DataSource {
    CACHE, REMOTE
}
