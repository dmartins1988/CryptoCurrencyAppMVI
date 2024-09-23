package com.example.cryptocurrencyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cryptocurrencyapp.presentation.crypto_currency_detail.CoinDetailViewModel
import com.example.cryptocurrencyapp.presentation.crypto_currency_list.CoinsViewModel
import com.example.cryptocurrencyapp.presentation.navigation.Screen
import com.example.cryptocurrencyapp.ui.coin_list_screen.CoinListScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    coinsViewModel: CoinsViewModel,
    coinDetailViewModel: CoinDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CoinList
    ) {
        composable<Screen.CoinList> {
            val state = coinsViewModel.state.collectAsStateWithLifecycle().value
            CoinListScreen(
                navController = navController,
                state = state
            )
        }

        composable<Screen.CoinDetail> {
            val state = coinDetailViewModel.state.collectAsStateWithLifecycle().value
        }
    }
}