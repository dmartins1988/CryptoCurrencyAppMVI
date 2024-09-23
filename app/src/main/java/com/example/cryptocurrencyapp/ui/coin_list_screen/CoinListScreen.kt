package com.example.cryptocurrencyapp.ui.coin_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cryptocurrencyapp.presentation.asUiText
import com.example.cryptocurrencyapp.presentation.crypto_currency_list.CoinsUiState
import com.example.cryptocurrencyapp.presentation.navigation.Screen

@Composable
fun CoinListScreen(
    navController: NavHostController,
    state: CoinsUiState
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .testTag("isLoading")
                        .align(Alignment.Center)
                )
            }

            state.listOfCoins.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .testTag("listOfWords")
                        .fillMaxSize()
                ) {
                    items(state.listOfCoins.size) { i ->
                        val coin = state.listOfCoins[i]
                        CoinItem(
                            coin = coin
                        ) {
                            navController.navigate(Screen.CoinDetail(coin.id))
                        }
                        if (i < state.listOfCoins.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }

            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage.asUiText().asString(),
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}