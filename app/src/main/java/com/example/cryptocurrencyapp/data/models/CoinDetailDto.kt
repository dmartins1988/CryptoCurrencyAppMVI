package com.example.cryptocurrencyapp.data.models


import com.example.cryptocurrencyapp.domain.models.CoinDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailDto(
    @SerialName("description")
    val description: String,
    @SerialName("development_status")
    val developmentStatus: String,
    @SerialName("first_data_at")
    val firstDataAt: String,
    @SerialName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerialName("hash_algorithm")
    val hashAlgorithm: String,
    @SerialName("id")
    val id: String,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("is_new")
    val isNew: Boolean,
    @SerialName("last_data_at")
    val SerialName: String,
    @SerialName("links")
    val links: Links,
    @SerialName("links_extended")
    val linksExtended: List<LinksExtended>,
    @SerialName("logo")
    val logo: String,
    @SerialName("message")
    val message: String,
    @SerialName("name")
    val name: String,
    @SerialName("open_source")
    val openSource: Boolean,
    @SerialName("org_structure")
    val orgStructure: String,
    @SerialName("proof_type")
    val proofType: String,
    @SerialName("rank")
    val rank: Int,
    @SerialName("started_at")
    val startedAt: String,
    @SerialName("symbol")
    val symbol: String,
    @SerialName("tags")
    val tags: List<Tag>,
    @SerialName("team")
    val team: List<Team>,
    @SerialName("type")
    val type: String,
    @SerialName("whitepaper")
    val whitepaper: Whitepaper
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name }
    )
}