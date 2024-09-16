package com.example.cryptocurrencyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocurrencyapp.presentation.crypto_currency_list.CoinsViewModel
import com.example.cryptocurrencyapp.presentation.navigation.Screen
import com.example.cryptocurrencyapp.ui.coin_list_screen.CoinListScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: CoinsViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CoinList
    ) {
        composable<Screen.CoinList> {
            CoinListScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<Screen.CoinDetail> {

        }
    }
}