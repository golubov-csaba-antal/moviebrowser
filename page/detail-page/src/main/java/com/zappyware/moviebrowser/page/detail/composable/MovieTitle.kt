package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.FavoriteIcon
import com.zappyware.moviebrowser.data.page.DetailPageWidget

@Composable
fun MovieTitle(
    pageWidget: DetailPageWidget?,
    isFavorite: Boolean,
    onFavoriteClicked: (String, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 16.dp,
) {
    pageWidget?.run {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            key(isFavorite) {
                FavoriteIcon(
                    modifier = Modifier.clickable {
                        onFavoriteClicked(id, !isFavorite)
                    },
                    contentId = id,
                    hideIfNotFavorite = false,
                )
            }
        }
    }
}
