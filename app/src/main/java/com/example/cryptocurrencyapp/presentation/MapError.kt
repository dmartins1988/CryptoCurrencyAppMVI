package com.example.cryptocurrencyapp.presentation

import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.core.DataError

fun DataError.asUiText(): UIText {
    return when (this) {
        DataError.Network.REQUEST_TIMEOUT -> UIText.StringResource(R.string.the_request_timed_out)
        DataError.Network.TOO_MANY_REQUESTS -> UIText.StringResource(R.string.youve_hit_your_rate_limit)
        DataError.Network.NO_INTERNET -> UIText.StringResource(R.string.no_internet)
        DataError.Network.PAYLOAD_TOO_LARGE -> UIText.StringResource(R.string.file_too_large)
        DataError.Network.SERVER_ERROR -> UIText.StringResource(R.string.server_error)
        DataError.Network.SERIALIZATION -> UIText.StringResource(R.string.error_serialization)
        DataError.Network.UNKNOWN -> UIText.StringResource(R.string.unknown_error)
    }
}