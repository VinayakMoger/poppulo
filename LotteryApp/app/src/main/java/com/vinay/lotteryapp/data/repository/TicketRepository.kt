package com.vinay.lotteryapp.data.repository

import com.vinay.lotteryapp.data.remote.api.KtorApiClient
import com.vinay.lotteryapp.data.remote.model.TicketDetailResponseModel
import com.vinay.lotteryapp.data.remote.model.TicketListResponseModel


class TicketRepository(private val apiClient: KtorApiClient) {

    suspend fun getTickets(): TicketListResponseModel = apiClient.getTicketList()

    suspend fun getTicketDetail(id:Int): TicketDetailResponseModel = apiClient.getTicketDetails(id)

}