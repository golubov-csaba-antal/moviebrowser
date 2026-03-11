package com.zappyware.moviebrowser.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.provider.DarkColorProvider
import com.zappyware.moviebrowser.common.ui.provider.LightColorProvider

@Composable
fun MovieBrowserTheme(
    childComposable: @Composable (() -> Unit)
) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) {
            darkColorScheme(onSurface = Color.White)
        } else {
            lightColorScheme()
        }
    ) {
        CompositionLocalProvider(
            LocalColorProvider provides if (isSystemInDarkTheme()) {
                DarkColorProvider(MaterialTheme.colorScheme)
            } else {
                LightColorProvider(MaterialTheme.colorScheme)
            }
        ) {
            childComposable()
        }
    }
}
