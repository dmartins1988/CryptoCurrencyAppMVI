package com.example.cryptocurrencyapp.presentation.crypto_currency_detail

import app.cash.turbine.test
import com.example.cryptocurrencyapp.core.DataError
import com.example.cryptocurrencyapp.core.Result
import com.example.cryptocurrencyapp.di.MainDispatcher
import com.example.cryptocurrencyapp.domain.CryptoCurrencyRepository
import com.example.cryptocurrencyapp.domain.models.CoinDetail
import com.example.cryptocurrencyapp.presentation.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinDetailViewModelTest {

    @get:Rule
    val mainDispatcher = MainDispatcherRule()

    private lateinit var repository: CryptoCurrencyRepository
    private lateinit var viewModel: CoinDetailViewModel

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
    }

    @Test
    fun `test state emits loading, then success`() = runTest {
        val coinDetail = CoinDetail(
            coinId = "1",
            name = "Bitcoin",
            description = "Description bitcoin",
            rank = 1,
            isActive = true
        )
        viewModel = CoinDetailViewModel(repository)

        coEvery { repository.getCoinDetail("1") } returns Result.Success(coinDetail)

        viewModel.dispatchIntent(CoinDetailIntent.LoadCoinDetail("1"))

        viewModel.state.test {

            assertEquals(CoinDetailUiState(isLoading = false), awaitItem())
            assertEquals(CoinDetailUiState(isLoading = true), awaitItem())
            assertEquals(CoinDetailUiState(isLoading = false, coin = coinDetail), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test state loading, then error`() = runTest {
        val result = Result.Error<CoinDetail, DataError.Network>(DataError.Network.UNKNOWN)
        viewModel = CoinDetailViewModel(repository)

        coEvery { repository.getCoinDetail("1") } returns result

        viewModel.dispatchIntent(CoinDetailIntent.LoadCoinDetail("1"))

        viewModel.state.test {
            assertEquals(CoinDetailUiState(isLoading = false), awaitItem())
            assertEquals(CoinDetailUiState(isLoading = true), awaitItem())
            assertEquals(CoinDetailUiState(isLoading = false, errorMessage = result.error), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}