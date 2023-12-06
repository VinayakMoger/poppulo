package com.vinay.lotteryapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketListResponseModel(@SerialName("tickets")var tickets : ArrayList<Ticket> = arrayListOf())

@Serializable
data class Ticket (
    @SerialName("id")var id      : Int? = null,
    @SerialName("created") var created : Long? = null)