package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.common.ui.ContentRating
import com.zappyware.moviebrowser.common.ui.FavoriteIcon
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.dropShadow
import com.zappyware.moviebrowser.common.ui.withShadow
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun MovieWidgetLandscapeComposable(
    modifier: Modifier,
    widget: MovieWidget,
    castShadow: Boolean = false,
    onDetailsClicked: (Widget) -> Unit,
) {
    val colorProvider = LocalColorProvider.current

    Row(
        modifier = modifier
            .dropShadow(castShadow, colorProvider.shadowColor, 24.dp, 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(colorProvider.imageBackgroundColor)
            .clickable {
                onDetailsClicked(widget)
            }
    ) {
        Box(
            modifier = Modifier.fillMaxHeight()
                .aspectRatio(2f/3f, true)
                .zIndex(1.0f)
        ) {
            AsyncImage(
                model = widget.smallPosterUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp)),
            )
            FavoriteIcon(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .zIndex(2.0f)
                    .align(Alignment.TopStart),
                contentId = widget.id,
            )
            Text(
                modifier = Modifier.align(Alignment.BottomStart)
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = widget.genre,
                style = MaterialTheme.typography.labelSmall.withShadow(castShadow = true, colorProvider.shadowColor),
                color = colorProvider.textColorLight,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2.0f)
                .padding(16.dp),
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = widget.overview,
                style = MaterialTheme.typography.labelSmall,
                color = colorProvider.textColorDark,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            ContentRating(
                modifier = Modifier.fillMaxWidth(),
                rating = widget.voteAverage,
                useDarkTextColor = true,
            )
        }
    }
}
