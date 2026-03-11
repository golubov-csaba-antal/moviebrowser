package com.zappyware.moviebrowser.common.ui

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

fun Modifier.graphicsLayer(
    pagerState: PagerState,
    pageIndex: Int,
    pageHeight: Float,
): Modifier =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction).absoluteValue
        val scale = lerp(
            start = 0.94f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
        scaleX = scale
        scaleY = scale
        translationY = (1f - scaleY) * -pageHeight
    }

fun Modifier.dropShadow(castShadow: Boolean, shadowColor: Color, shapeRadius: Dp, shadowRadius: Dp): Modifier =
    if (castShadow) {
        dropShadow(
            shape = RoundedCornerShape(shapeRadius),
            shadow = Shadow(
                radius = shadowRadius,
                color = shadowColor
            ),
        )
    } else {
        this
    }

fun TextStyle.withShadow(castShadow: Boolean, shadowColor: Color): TextStyle =
    if (castShadow) {
        copy(
            shadow = androidx.compose.ui.graphics.Shadow(
                color = shadowColor, offset = Offset(5f, 5f), blurRadius = 5f
            )
        )
    } else {
        this
    }
