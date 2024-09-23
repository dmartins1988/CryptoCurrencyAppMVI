package com.example.cryptocurrencyapp.presentation.crypto_currency_list

import app.cash.turbine.test
import com.example.cryptocurrencyapp.core.DataError
import com.example.cryptocurrencyapp.core.Result
import com.example.cryptocurrencyapp.domain.CryptoCurrencyRepository
import com.example.cryptocurrencyapp.domain.models.Coin
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoinsViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var repository: CryptoCurrencyRepository
    private lateinit var viewModel: CoinsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test state emits Loading, then Success`() = runTest {
        // Mock the repository to return a successful result.
        val coins = listOf(
            Coin(
                id = "1",
                isActive = false,
                isNew = false,
                name = "Bitcoin",
                rank = 1,
                symbol = ""
            ),
            Coin(
                id = "2",
                isActive = true,
                isNew = false,
                name = "Ethereum",
                rank = 2,
                symbol = ""
            )
        )

        viewModel = CoinsViewModel(repository)

        coEvery { repository.getCoins() } returns Result.Success(coins)

        // Collect emissions from the ViewModel's state flow.
        viewModel.state.test {
            assertEquals(CoinsUiState(isLoading = false), awaitItem())
            assertEquals(CoinsUiState(isLoading = true), awaitItem())
            assertEquals(CoinsUiState(isLoading = false, listOfCoins = coins), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test state emits Loading, then Error`() = runTest {
        // Mock the repository to return an error result.
        val result = Result.Error<List<Coin>, DataError.Network>(DataError.Network.UNKNOWN)
        coEvery { repository.getCoins() } returns result

        viewModel = CoinsViewModel(repository)

        // Collect emissions from the ViewModel's state flow.
        viewModel.state.test {
            assertEquals(CoinsUiState(isLoading = false), awaitItem())
            assertEquals(CoinsUiState(isLoading = true), awaitItem())
            assertEquals(
                CoinsUiState(
                    isLoading = false,
                    errorMessage = result.error
                ), awaitItem()
            )
            cancelAndIgnoreRemainingEvents()
        }
    }
}