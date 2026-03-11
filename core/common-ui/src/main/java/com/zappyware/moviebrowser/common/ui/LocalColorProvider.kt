package com.zappyware.moviebrowser.common.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

interface ColorProvider {
    val shadowColor: Color
    val imageBackgroundColor: Color
    val imageBorderColor: Color
    val textColorDark: Color
    val textColorLight: Color
    val rateIconColor: Color
}

val LocalColorProvider = compositionLocalOf<ColorProvider> { error("No color provider defined") }
