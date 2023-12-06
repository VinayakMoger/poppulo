package com.vinay.lotteryapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vinay.lotteryapp.data.remote.model.Ticket
import com.vinay.lotteryapp.ui.viewmodel.TicketViewModel


@Composable
fun TicketListView(tickets:ArrayList<Ticket>,viewModel: TicketViewModel) {
    LazyColumn{
        items(tickets) {ticket->
            TicketItem(ticket = ticket, viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketItem(ticket: Ticket,viewModel: TicketViewModel) {
    Card(modifier = Modifier
        .height(80.dp)
        .fillMaxWidth()
        .padding(10.dp), onClick ={
        ticket.id?.let { viewModel.fetchTicketInfo(it) }

    }, shape = RoundedCornerShape(5.dp) ) {
        Text(text = "Ticket ${ticket.id}", style = TextStyle(fontSize = 20.sp), modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .wrapContentWidth())
    }
}

@Composable
fun TicketDetailView(amount:Int?) {
   Surface {
       Card(modifier = Modifier
           .height(120.dp)
           .fillMaxWidth()
           .padding(10.dp), shape = RoundedCornerShape(5.dp) ) {
           Text(text = "Total Wins: € $amount", style = TextStyle(fontSize = 20.sp), modifier = Modifier
               .fillMaxSize()
               .wrapContentHeight()
               .wrapContentWidth())
       }
   }

}


