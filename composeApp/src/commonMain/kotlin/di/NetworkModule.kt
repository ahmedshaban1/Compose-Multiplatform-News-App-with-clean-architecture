package di

import remote.requester.RequestHandler
import com.ahmed.shaban.remote.requester.RequestHandlerImpl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                        coerceInputValues = true
                    }
                )
            }
            HttpResponseValidator {
                validateResponse { response ->
                    val error: Error = response.body()
                    println(error)
                }
            }
        }
    }

    single<RequestHandler> {
        RequestHandlerImpl()
    }
}
