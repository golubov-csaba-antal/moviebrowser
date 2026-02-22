package com.zappyware.moviebrowser.common.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

@Composable
fun Modifier.shimmer(isLoading: Boolean): Modifier {
    return if (isLoading) {
        val shimmerColors = if (isSystemInDarkTheme()) {
            listOf(
                Color("#000000".toColorInt()),
                Color("#232323".toColorInt()),
                Color("#000000".toColorInt()),
            )
        } else {
            listOf(
                Color("#FFFFFF".toColorInt()),
                Color("#DCDCDC".toColorInt()),
                Color("#FFFFFF".toColorInt()),
            )
        }

        val transition = rememberInfiniteTransition(label = "shimmer_animation")
        val translateAnim by transition.animateFloat(
            initialValue = -2400f,
            targetValue = 2400f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = 3600,
                    easing = FastOutSlowInEasing
                )
            ),
        )

        drawWithCache {
            val brush = Brush.linearGradient(
                colors = shimmerColors,
                start = Offset(translateAnim, 0f),
                end = Offset(translateAnim + size.width * 2f, size.height)
            )
            onDrawWithContent {
                drawRoundRect(
                    brush = brush,
                    size = size
                )
            }
        }
    } else {
        this
    }
}
