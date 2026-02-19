package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.R
import com.zappyware.moviebrowser.data.Movie

@Composable
fun MovieMeta(
    movie: State<Movie?>,
    isFavoriteState: State<Boolean>,
    modifier: Modifier,
    onFavoriteClicked: (Long,Boolean) -> Unit,
) {
    val rememberMovie = movie.value
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = rememberMovie?.title.orEmpty(),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_favorite),
                contentDescription = null,
                modifier = Modifier.clickable {
                    rememberMovie?.let {
                        onFavoriteClicked(it.id, !isFavoriteState.value)
                    }
                },
                tint = if (isFavoriteState.value) {
                    Color.Yellow
                } else {
                    LocalContentColor.current
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = rememberMovie?.overview.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}
