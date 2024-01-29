package com.ahmed.shaban.remote.requester

import com.ahmed.shaban.remote.ResultWrapper
import com.ahmed.shaban.remote.errorhandling.ErrorCodes
import com.ahmed.shaban.remote.errorhandling.ErrorCodes.CONNECTION_ERROR
import com.ahmed.shaban.remote.errorhandling.ErrorCodes.ERROR_TIME_OUT
import com.ahmed.shaban.remote.errorhandling.ErrorCodes.GENERIC_ERROR
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.TimeoutCancellationException

class RequestHandlerImpl : RequestHandler {
    override suspend fun <T> makeApiRequest(
        call: suspend () -> T?,
    ): ResultWrapper<T> {
        return try {
            call.invoke()?.let {
                ResultWrapper.Success(it)
            } ?: kotlin.run {
                ResultWrapper.GenericError(ErrorCodes.NO_DATA_FOUND)
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    ResultWrapper.GenericError(ERROR_TIME_OUT)
                }
                is IOException -> {
                    ResultWrapper.GenericError(
                        CONNECTION_ERROR
                    )
                }
               /* is HttpException -> {
                    ResultWrapper.GenericError(
                        errorCode = throwable.response()?.code() ?: GENERIC_ERROR
                    )
                }*/
                else -> {
                    ResultWrapper.GenericError(GENERIC_ERROR)
                }
            }
        }
    }
}
