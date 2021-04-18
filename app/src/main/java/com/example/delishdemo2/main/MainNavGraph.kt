package com.example.delishdemo2.main

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.delishdemo2.message.MessageViewModel

object MainDestinations {
    const val MAIN_ROUTE = "main"
}

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@VisibleForTesting
@Composable
fun MainNavGraph(
    startDestination: String = MainDestinations.MAIN_ROUTE,
    onDetails: (Int, String) -> Unit,
) {

    val messageViewModel: MessageViewModel = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable((MainDestinations.MAIN_ROUTE)) {
            MainScreen(
                viewModel = messageViewModel,
                onDetails
            )
        }
    }
}
