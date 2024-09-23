package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.core.DataError
import com.example.cryptocurrencyapp.core.Result
import com.example.cryptocurrencyapp.data.models.toCoin
import com.example.cryptocurrencyapp.data.models.toCoinDetail
import com.example.cryptocurrencyapp.data.remote.CryptoCurrencyService
import com.example.cryptocurrencyapp.di.IODispatcher
import com.example.cryptocurrencyapp.domain.CryptoCurrencyRepository
import com.example.cryptocurrencyapp.domain.models.Coin
import com.example.cryptocurrencyapp.domain.models.CoinDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CryptoCurrencyRepositoryImpl @Inject constructor(
    private val service: CryptoCurrencyService,
    @IODispatcher private val ioScope: CoroutineDispatcher
) : CryptoCurrencyRepository {
    override suspend fun getCoins(): Result<List<Coin>, DataError.Network> {
        return withContext(ioScope) {
            try {
                val listOfCoins = service.getCoins().map { it.toCoin() }
                Result.Success(data = listOfCoins)
            } catch (e: HttpException) {
                when (e.code()) {
                    408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                    413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                    else -> Result.Error(DataError.Network.UNKNOWN)
                }
            } catch (e: IOException) {
                Result.Error(DataError.Network.NO_INTERNET)
            }
        }
    }

    override suspend fun getCoinDetail(coinId: String): Result<CoinDetail, DataError.Network> {
        return withContext(ioScope) {
            try {
                val coinDetail = service.getCoinDetail(coinId).toCoinDetail()
                Result.Success(coinDetail)
            } catch (e: HttpException) {
                when (e.code()) {
                    408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
                    413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
                    else -> Result.Error(DataError.Network.UNKNOWN)
                }
            } catch (e: IOException) {
                Result.Error(DataError.Network.NO_INTERNET)
            }
        }
    }
}