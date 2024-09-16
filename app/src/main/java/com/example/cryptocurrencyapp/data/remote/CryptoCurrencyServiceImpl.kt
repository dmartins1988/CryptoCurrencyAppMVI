package com.example.cryptocurrencyapp.data.remote

import com.example.cryptocurrencyapp.data.models.CoinDetailDto
import com.example.cryptocurrencyapp.data.models.CoinDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class CryptoCurrencyServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : CryptoCurrencyService {
    override suspend fun getCoins(): List<CoinDto> {
        return httpClient.get("/v1/coins").body()
    }

    override suspend fun getCoinDetail(coinId: String): CoinDetailDto {
        return httpClient.get("/v1/coins/$coinId").body()
    }
}