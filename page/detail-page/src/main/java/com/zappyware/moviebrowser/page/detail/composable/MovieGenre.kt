package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.data.page.DetailPageWidget

@Composable
fun MovieGenre(
    pageWidget: DetailPageWidget?,
    horizontalPadding: Dp = 16.dp,
) {
    pageWidget?.run {
        genres.takeIf { it.isNotEmpty() }?.let { genres ->
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = horizontalPadding),
                text = genres,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
