package remote.requester

import remote.ResultWrapper

interface RequestHandler {
    suspend fun <T> makeApiRequest(
        call: suspend () -> T?
    ): ResultWrapper<T>
}
