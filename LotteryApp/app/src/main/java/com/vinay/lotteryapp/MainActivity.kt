package com.vinay.lotteryapp

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vinay.lotteryapp.ui.TicketDetailView
import com.vinay.lotteryapp.ui.TicketListView
import com.vinay.lotteryapp.ui.theme.LotteryAppTheme
import com.vinay.lotteryapp.ui.viewmodel.TicketViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val ticketViewModel: TicketViewModel by viewModels()
    private lateinit var navController: NavHostController
    private lateinit var  sharedPref :SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences("lotteryAppData",0)
        setContent {
            navController = rememberNavController()
            val tickets by ticketViewModel.tickets.collectAsState()
            LotteryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "TicketList",){
                        composable("TicketList") { TicketListView(tickets.tickets,ticketViewModel) }
                        composable("TicketDetail/{amount}") {  backStackEntry ->
                            val amount = backStackEntry.arguments?.getString("amount")
                            TicketDetailView(amount?.toIntOrNull())
                        }
                    }
                }
            }
        }

        /**
         * collect ticket Amount from view model and navigate to the screen
         */
        lifecycleScope.launch{
            ticketViewModel.ticketAmount.collect{
                if(it!=-1) {
                    val totalAmount = it+sharedPref.getInt("amount",0)
                    sharedPref.edit().putInt("amount",totalAmount).apply()
                    navController.navigate("TicketDetail/$totalAmount")
                    ticketViewModel._ticketAmount.value= -1

                }
            }
        }

    }
}

