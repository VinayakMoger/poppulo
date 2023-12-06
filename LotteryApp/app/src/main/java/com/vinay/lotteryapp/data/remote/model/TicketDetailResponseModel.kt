package com.vinay.lotteryapp.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketDetailResponseModel(@SerialName("id") val id:Int?=null, @SerialName("numbers") val numbers:IntArray?=null)
