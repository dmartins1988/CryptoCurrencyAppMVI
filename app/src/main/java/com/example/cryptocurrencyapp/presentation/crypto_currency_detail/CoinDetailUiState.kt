package com.example.cryptocurrencyapp.presentation.crypto_currency_detail

import com.example.cryptocurrencyapp.core.DataError
import com.example.cryptocurrencyapp.domain.models.CoinDetail

data class CoinDetailUiState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val errorMessage: DataError.Network? = null
)
