package com.example.cryptocurrencyapp.data.remote

import com.example.cryptocurrencyapp.data.models.CoinDetailDto
import com.example.cryptocurrencyapp.data.models.CoinDto

interface CryptoCurrencyService {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinDetail(coinId: String): CoinDetailDto
}