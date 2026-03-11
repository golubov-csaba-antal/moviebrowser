package com.zappyware.moviebrowser.common.ui

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp
import java.util.Locale

@Composable
fun ContentRating(
    modifier: Modifier,
    rating: Float?,
    castShadow: Boolean = false,
    useDarkTextColor: Boolean = false,
) {
    if (null == rating) return

    val colorProvider = LocalColorProvider.current

    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            appendInlineContent("star")
            append(String.format(Locale.getDefault(), "%.1f", rating))
        },
        inlineContent = mapOf(
            "star" to InlineTextContent(
                Placeholder(12.sp, 12.sp, PlaceholderVerticalAlign.TextCenter),
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_rate),
                    contentDescription = null,
                    tint = if (useDarkTextColor) colorProvider.textColorDark else colorProvider.rateIconColor,
                )
            }
        ),
        style = MaterialTheme.typography.labelSmall.withShadow(castShadow, colorProvider.shadowColor),
        color = if (useDarkTextColor) colorProvider.textColorDark else colorProvider.textColorLight,
    )
}
