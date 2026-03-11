package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.dropShadow
import com.zappyware.moviebrowser.data.widget.ImageWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun ImageWidgetComposable(
    modifier: Modifier,
    widget: ImageWidget,
    castShadow: Boolean = false,
    onDetailsClicked: (Widget) -> Unit
) {
    val colorProvider = LocalColorProvider.current

    Box(
        modifier = modifier
            .dropShadow(castShadow, colorProvider.shadowColor, 24.dp, 16.dp)
            .clickable {
                onDetailsClicked(widget)
            },
    ) {
        AsyncImage(
            model = widget.imagePath,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1.0f)
                .clip(RoundedCornerShape(24.dp))
                .background(colorProvider.imageBackgroundColor),
        )
    }
}
