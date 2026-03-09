package com.zappyware.moviebrowser.common.ui.trays

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.common.ui.FavoriteIcon
import com.zappyware.moviebrowser.data.tray.ShowcaseTrayWidget
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun ShowcaseTrayWidgetComposable(
    tray: ShowcaseTrayWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val onDetailsClickedCallback = remember(onDetailsClicked) {
        { movieWidget: MovieWidget ->
            onDetailsClicked(movieWidget)
        }
    }

    Column {
        Text(
            text = tray.title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .height(48.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        val windowInfo = LocalWindowInfo.current

        val posterWidth = windowInfo.containerDpSize.width
        val posterHeight = posterWidth / 2f * 3f

        val backgroundColorColor = if (isSystemInDarkTheme()) {
            Color.DarkGray
        } else {
            Color.LightGray
        }

        val movieWidget = tray.widget as MovieWidget

        Box(
            modifier = Modifier
                .size(posterWidth, posterHeight)
                .background(backgroundColorColor)
                .clickable {
                    onDetailsClickedCallback(movieWidget)
                },
        ) {
            AsyncImage(
                model = movieWidget.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1.0f),
            )
            FavoriteIcon(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .zIndex(2.0f)
                    .align(Alignment.TopStart),
                contentId = movieWidget.id,
            )
            Column(
                modifier = Modifier
                    .zIndex(2.0f)
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
            ) {
                Text(
                    text = movieWidget.title,
                    style = MaterialTheme.typography.labelLarge.copy(
                        shadow = Shadow(
                            color = Color.Black, offset = Offset(5f, 5f), blurRadius = 5f
                        )
                    ),
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movieWidget.genre,
                    style = MaterialTheme.typography.labelSmall.copy(
                        shadow = Shadow(
                            color = Color.Black, offset = Offset(5f, 5f), blurRadius = 5f
                        )
                    ),
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(progress = { movieWidget.voteAverage / 10.0f }, modifier = Modifier.fillMaxWidth(), drawStopIndicator = { })
            }
        }
    }
}
