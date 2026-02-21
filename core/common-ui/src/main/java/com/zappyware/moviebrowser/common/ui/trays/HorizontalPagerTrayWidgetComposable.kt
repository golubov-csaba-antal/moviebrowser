package com.zappyware.moviebrowser.common.ui.trays

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.zappyware.moviebrowser.common.ui.widgets.MovieWidgetComposable
import com.zappyware.moviebrowser.data.MovieWidget
import com.zappyware.moviebrowser.data.TrayWidget
import com.zappyware.moviebrowser.data.Widget
import kotlin.math.absoluteValue

@Composable
fun HorizontalPagerTrayWidgetComposable(
    tray: TrayWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val onDetailsClickedCallback = remember(onDetailsClicked) {
        { movieWidget: MovieWidget ->
            onDetailsClicked(movieWidget)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = tray.title,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .height(48.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        val pagerState = rememberPagerState(pageCount = { tray.widgets.size })
        val shadowColor = if (isSystemInDarkTheme()) {
            Color.Black
        } else {
            Color.DarkGray
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp, vertical = 0.dp),
            pageSpacing = 24.dp,
            pageSize = PageSize.Fixed(196.dp),
            key = { index -> tray.widgets[index].id }
        ) { pageIndex ->
            MovieWidgetComposable(
                modifier = Modifier
                    .graphicsLayer {
                        val pageOffset =
                            ((pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction).absoluteValue
                        val scale = lerp(
                            start = 0.94f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = scale
                        scaleY = scale
                        translationY = (1f - scaleY) * -270.dp.value
                    }
                    .dropShadow(
                        shape = RoundedCornerShape(24.dp),
                        shadow = Shadow(
                            radius = 16.dp,
                            color = shadowColor
                        ),
                    ),
                movieWidget = tray.widgets[pageIndex] as MovieWidget,
                onDetailsClickedCallback,
            )
        }
    }
}
