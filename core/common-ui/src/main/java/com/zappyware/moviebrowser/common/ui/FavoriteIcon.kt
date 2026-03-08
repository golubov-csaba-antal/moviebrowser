package com.zappyware.moviebrowser.common.ui

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

@Composable
fun FavoriteIcon(
    modifier: Modifier,
    contentId: String,
) {
    var isFavorite by remember { mutableStateOf(false) }

    val favoriteProvider = LocalFavoriteProvider.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(contentId) {
        isFavorite = scope.async(Dispatchers.IO) {
            favoriteProvider.isFavorite(contentId)
        }.await()
    }

    if (isFavorite) {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite),
            contentDescription = null,
            tint = Color.Red,
            modifier = modifier,
        )
    } else {
        // don't show anything if not favorite
    }
}
