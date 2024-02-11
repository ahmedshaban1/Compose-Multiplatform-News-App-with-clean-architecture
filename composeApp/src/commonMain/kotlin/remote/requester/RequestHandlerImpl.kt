package com.ahmed.shaban.remote.requester

import remote.errorhandling.ErrorCodes
import remote.errorhandling.ErrorCodes.CONNECTION_ERROR
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException
import remote.ResultWrapper
import remote.requester.RequestHandler

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
        } catch (e: ClientRequestException) {
            println(e.response.status.toString())
            ResultWrapper.GenericError(
                CONNECTION_ERROR
            )
        } catch (e: ServerResponseException) {
            println(e.response.status.toString())
            ResultWrapper.GenericError(
                CONNECTION_ERROR
            )
        } catch (e: IOException) {
            println(e.toString())
            ResultWrapper.GenericError(
                CONNECTION_ERROR
            )
        } catch (e: SerializationException) {
            println(e.toString())
            ResultWrapper.GenericError(
                CONNECTION_ERROR
            )
        } catch (e: Exception) {
            println(e.toString())
            ResultWrapper.GenericError(
                CONNECTION_ERROR
            )
        }
    }
}
