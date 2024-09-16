package com.example.cryptocurrencyapp.presentation.crypto_currency_list

import com.example.cryptocurrencyapp.core.DataError
import com.example.cryptocurrencyapp.domain.models.Coin

data class CoinsUiState(
    val isLoading: Boolean = false,
    val listOfCoins: List<Coin> = emptyList(),
    val errorMessage: DataError.Network? = null
)