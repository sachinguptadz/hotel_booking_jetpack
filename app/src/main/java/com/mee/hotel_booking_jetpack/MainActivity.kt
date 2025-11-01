package com.mee.hotel_booking_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.mee.hotel_booking_jetpack.ui.screens.HomeExploreScreen
import com.mee.hotel_booking_jetpack.ui.screens.ResortDetailScreen
import com.mee.hotel_booking_jetpack.ui.theme.Hotel_booking_jetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hotel_booking_jetpackTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeExploreScreen { selectedCard ->

                navController.currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("selectedResort", selectedCard)
                navController.navigate("detail")
            }
        }

        composable("detail") {
            val data = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Map<String, String>>("selectedResort")

            if (data != null) {
                ResortDetailScreen(
                    data = data,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}