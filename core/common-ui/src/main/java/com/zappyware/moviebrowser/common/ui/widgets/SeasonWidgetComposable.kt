package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.data.widget.SeasonWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun SeasonWidgetComposable(
    modifier: Modifier,
    widget: SeasonWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val backgroundColor = if (isSystemInDarkTheme()) {
        Color.DarkGray
    } else {
        Color.LightGray
    }
    val textColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .clickable {
                onDetailsClicked(widget)
            }
    ) {
        AsyncImage(
            model = widget.smallPosterPath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(2f/3f, true)
                .zIndex(1.0f)
                .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2.0f)
                .padding(16.dp),
        ) {
            Text(
                text = widget.name,
                style = MaterialTheme.typography.labelLarge,
                color = textColor,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = widget.overview,
                style = MaterialTheme.typography.labelSmall,
                color = textColor,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(progress = { widget.voteAverage / 10.0f }, modifier = Modifier.fillMaxWidth(), drawStopIndicator = { })
        }
    }
}
