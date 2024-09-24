package com.example.cryptocurrencyapp.ui.coin_detail_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.presentation.UIText
import com.example.cryptocurrencyapp.presentation.asUiText
import com.example.cryptocurrencyapp.presentation.crypto_currency_detail.CoinDetailIntent
import com.example.cryptocurrencyapp.presentation.crypto_currency_detail.CoinDetailViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinDetailViewModel,
    coinId: String
) {

    LaunchedEffect(coinId) {
        viewModel.dispatchIntent(CoinDetailIntent.LoadCoinDetail(coinId))
    }

    val state = viewModel.state.collectAsStateWithLifecycle().value

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Center)
                )
            }
            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage.asUiText().asString(),
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            else -> {
                state.coin?.let { coin ->
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(20.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "${coin.rank}. ${coin.name} ${coin.symbol}",
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.weight(8f)
                                )
                                Text(
                                    text = if (coin.isActive) UIText.StringResource(R.string.active)
                                        .asString() else UIText.StringResource(R.string.inactive)
                                        .asString(),
                                    color = if (coin.isActive) Color.Green else Color.Red,
                                    fontStyle = FontStyle.Italic,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .align(CenterVertically)
                                        .weight(2f)
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = coin.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            Text(
                                text = "Tags",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                coin.tags.forEach { tag ->
                                    CoinTag(tag = tag)
                                }
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }
        }
    }
}