package com.example.cryptocurrencyapp.domain

import com.example.cryptocurrencyapp.core.DataError
import com.example.cryptocurrencyapp.core.Result
import com.example.cryptocurrencyapp.domain.models.Coin

interface CryptoCurrencyRepository {
    suspend fun getCoins(): Result<List<Coin>, DataError.Network>
}