package com.example.cryptocurrencyapp.domain

import com.example.cryptocurrencyapp.core.DataError
import com.example.cryptocurrencyapp.core.Result
import com.example.cryptocurrencyapp.domain.models.Coin
import com.example.cryptocurrencyapp.domain.models.CoinDetail

interface CryptoCurrencyRepository {
    suspend fun getCoins(): Result<List<Coin>, DataError.Network>
    suspend fun getCoinDetail(coinId: String): Result<CoinDetail, DataError.Network>
}