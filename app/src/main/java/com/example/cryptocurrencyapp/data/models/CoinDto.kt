package com.example.cryptocurrencyapp.data.models

import com.example.cryptocurrencyapp.domain.models.Coin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    @SerialName("is_active")
    val isActive: Boolean? = null,
    @SerialName("is_new")
    val isNew: Boolean? = null,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive ?: false,
        isNew = isNew ?: false,
        name = name,
        rank = rank,
        symbol = symbol
    )
}
