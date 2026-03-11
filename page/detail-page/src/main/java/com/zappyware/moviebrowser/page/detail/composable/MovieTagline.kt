package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.data.page.DetailPageWidget

@Composable
fun MovieTagline(
    pageWidget: DetailPageWidget?,
    horizontalPadding: Dp = 16.dp,
) {
    pageWidget?.run {
        tagline.takeIf { it.isNotEmpty() }?.let { tagline ->
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = horizontalPadding),
                text = "\"$tagline\"",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic
            )
        }
    }
}
