package com.vinay.lotteryapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinay.lotteryapp.data.remote.api.KtorApiClient
import com.vinay.lotteryapp.data.remote.model.Ticket
import com.vinay.lotteryapp.data.remote.model.TicketDetailResponseModel
import com.vinay.lotteryapp.data.remote.model.TicketListResponseModel
import com.vinay.lotteryapp.data.repository.TicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel : ViewModel() {

    private val _tickets = MutableStateFlow(TicketListResponseModel())
    val tickets:StateFlow<TicketListResponseModel> = _tickets

    val _ticketAmount = MutableStateFlow(-1)
    val ticketAmount:StateFlow<Int> = _ticketAmount

    val repository:TicketRepository = TicketRepository(KtorApiClient())

   init {
       fetchTickets()
   }

    /**
     * fetch ticketList
     */
    private fun fetchTickets() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchTickets = repository.getTickets()
                _tickets.emit(fetchTickets)
            } catch (e:Exception) {
                Log.i("exception","${e.message}")
                // Handle Error
            }
        }
    }

    /**
     * get ticket details based on the id
     */
    fun fetchTicketInfo(id:Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchTicketInfo = repository.getTicketDetail(id)
                _ticketAmount.emit(getAmount(fetchTicketInfo.numbers))
            } catch (e:Exception) {
                _ticketAmount.emit(-1)
            }
        }
    }

    /**
     * return amount based on the number in the array
     *
     */
     fun getAmount(numbers:IntArray?):Int {
        var amount = 0
        if(numbers != null && numbers.isNotEmpty()) {
            amount += if (numbers.all { numbers[0] == it }) 5 else 0
            amount += if (numbers.sum() == 2) 10 else 0
            amount += if (numbers.first() != numbers.getOrNull(1) && numbers.first() != numbers.getOrNull(
                    2
                )
            ) 1 else 0
        }

        return amount
    }

}