package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun MovieWidgetCircularComposable(
    modifier: Modifier,
    widget: MovieWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val backgroundColor = if (isSystemInDarkTheme()) {
        Color.DarkGray
    } else {
        Color.LightGray
    }

    Box(
        modifier = modifier
            .clickable {
                onDetailsClicked(widget)
            },
    ) {
        AsyncImage(
            model = widget.posterUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1.0f)
                .clip(RoundedCornerShape(60.dp))
                .background(backgroundColor)
                .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape),
        )
    }
}
