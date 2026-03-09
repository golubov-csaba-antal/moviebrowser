package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.common.ui.FavoriteIcon
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun MovieWidgetPortraitComposable(
    modifier: Modifier,
    widget: MovieWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    Box(
        modifier = modifier
            .clickable {
                onDetailsClicked(widget)
            },
    ) {
        AsyncImage(
            model = widget.smallPosterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1.0f)
                .clip(RoundedCornerShape(24.dp)),
        )
        FavoriteIcon(
            modifier = Modifier
                .padding(all = 16.dp)
                .zIndex(2.0f)
                .align(Alignment.TopStart),
            contentId = widget.id,
        )
        Column(
            modifier = Modifier
                .zIndex(2.0f)
                .padding(16.dp)
                .align(Alignment.BottomEnd),
        ) {
            Text(
                text = widget.title,
                style = MaterialTheme.typography.labelLarge.copy(
                    shadow = Shadow(
                        color = Color.Black, offset = Offset(5f, 5f), blurRadius = 5f
                    )
                ),
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = widget.genre,
                style = MaterialTheme.typography.labelSmall.copy(
                    shadow = Shadow(
                        color = Color.Black, offset = Offset(5f, 5f), blurRadius = 5f
                    )
                ),
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(progress = { widget.voteAverage / 10.0f }, modifier = Modifier.fillMaxWidth(), drawStopIndicator = { })
        }
    }
}
