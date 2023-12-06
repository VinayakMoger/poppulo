package com.vinay.lotteryapp.data.remote.api

import com.vinay.lotteryapp.BuildConfig
import com.vinay.lotteryapp.data.remote.model.TicketDetailResponseModel
import com.vinay.lotteryapp.data.remote.model.TicketListResponseModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom

class KtorApiClient {
    private val TIME_OUT = 6000
    private val httpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer= KotlinxSerializer(kotlinx.serialization.json.Json{
                prettyPrint= true
                isLenient= true
                ignoreUnknownKeys= true
            })

            engine {
                connectTimeout= TIME_OUT
                socketTimeout= TIME_OUT
            }
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    suspend fun getTicketList(): TicketListResponseModel {
        val url = URLBuilder().apply {
            takeFrom(BuildConfig.BASE_URL+"prod/ticket") // Replace with your API endpoint
        }
        return httpClient.get(url.build())
    }

    suspend fun getTicketDetails(id:Int): TicketDetailResponseModel {
        val url = URLBuilder().apply {
            takeFrom(BuildConfig.BASE_URL+"prod/ticket/$id") // Replace with your API endpoint
        }
        return httpClient.get(url.build())
    }
}