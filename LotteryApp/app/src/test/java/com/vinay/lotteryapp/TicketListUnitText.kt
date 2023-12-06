package com.vinay.lotteryapp

import com.vinay.lotteryapp.ui.viewmodel.TicketViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.Mock

class TicketListUnitText {
    @Mock
    lateinit var viewModel: TicketViewModel
    @Test
    fun checkTicketList() {
        viewModel = TicketViewModel()
        val data = viewModel.getAmount(intArrayOf(0,0,0))
        assertEquals(5, data)
        val data2 = viewModel.getAmount(intArrayOf(0,1,1))
        assertEquals(data2, 11)
        val data3 = viewModel.getAmount(intArrayOf(1,0,1))
        assertEquals(data3, 10)

    }

}