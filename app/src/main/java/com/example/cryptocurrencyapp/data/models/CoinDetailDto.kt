package com.example.cryptocurrencyapp.data.models


import com.example.cryptocurrencyapp.domain.models.CoinDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailDto(
    @SerialName("description")
    val description: String?,
    @SerialName("development_status")
    val developmentStatus: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("is_active")
    val isActive: Boolean?,
    @SerialName("is_new")
    val isNew: Boolean?,
    @SerialName("name")
    val name: String?,
    @SerialName("rank")
    val rank: Int?,
    @SerialName("symbol")
    val symbol: String?,
    @SerialName("tags")
    val tags: List<Tag>?,
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id ?: "",
        name = name ?: "",
        description = description ?: "",
        symbol = symbol ?: "",
        rank = rank ?: 0,
        isActive = isActive ?: false,
        tags = tags?.map { it.name } ?: emptyList()
    )
}