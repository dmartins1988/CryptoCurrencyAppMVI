package com.example.cryptocurrencyapp.presentation.crypto_currency_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.core.Result
import com.example.cryptocurrencyapp.domain.CryptoCurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val repository: CryptoCurrencyRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailUiState())
    val state: StateFlow<CoinDetailUiState> = _state.asStateFlow()

    fun dispatchIntent(intent: CoinDetailIntent) {
        when (intent) {
            is CoinDetailIntent.LoadCoinDetail -> loadCoinDetail(intent.coinId)
        }
    }

    private fun loadCoinDetail(coinId: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)
            }
            when (val response = repository.getCoinDetail(coinId)) {
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = response.error
                        )
                    }
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coin = response.data
                        )
                    }
                }
            }
        }
    }
}