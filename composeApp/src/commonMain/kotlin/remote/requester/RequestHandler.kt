package com.ahmed.shaban.remote.requester

import com.ahmed.shaban.remote.ResultWrapper

interface RequestHandler {
    suspend fun <T> makeApiRequest(
        call: suspend () -> T?
    ): ResultWrapper<T>
}
