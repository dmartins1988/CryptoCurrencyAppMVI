package com.example.cryptocurrencyapp.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UIText {
    data class StringDynamic(val value: String) : UIText()
    class StringResource(
        @StringRes val resId: Int,
        val args: Array<Any> = arrayOf()
    ) : UIText()

    @Composable
    fun asString(): String {
        return when (this) {
            is StringDynamic -> value
            is StringResource -> stringResource(resId, *args)
        }
    }
}