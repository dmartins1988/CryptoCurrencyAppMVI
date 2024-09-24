package com.example.cryptocurrencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.presentation.crypto_currency_detail.CoinDetailViewModel
import com.example.cryptocurrencyapp.presentation.crypto_currency_list.CoinsViewModel
import com.example.cryptocurrencyapp.ui.navigation.MainNavigation
import com.example.cryptocurrencyapp.ui.theme.CryptoCurrencyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val coinListViewModel: CoinsViewModel by viewModels()
    private val coinDetailViewModel: CoinDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoCurrencyAppTheme {
                val navController = rememberNavController()
                Scaffold { innerPadding ->
                    MainNavigation(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        coinsViewModel = coinListViewModel,
                        coinDetailViewModel = coinDetailViewModel
                    )
                }
            }
        }
    }
}