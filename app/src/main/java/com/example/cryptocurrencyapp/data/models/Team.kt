package com.example.cryptocurrencyapp.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("position")
    val position: String
)