package com.zappyware.moviebrowser.common.ui.trays

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.zappyware.moviebrowser.common.ui.widgets.ImageWidgetComposable
import com.zappyware.moviebrowser.common.ui.widgets.MovieWidgetComposable
import com.zappyware.moviebrowser.common.ui.widgets.PeopleWidgetComposable
import com.zappyware.moviebrowser.common.ui.widgets.VideoWidgetComposable
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.data.widget.ImageWidget
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.PeopleWidget
import com.zappyware.moviebrowser.data.widget.VideoWidget
import com.zappyware.moviebrowser.data.widget.Widget
import kotlin.math.absoluteValue

@Composable
fun HorizontalPagerTrayWidgetComposable(
    tray: HorizontalPagerTrayWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val onDetailsClickedCallback = remember(onDetailsClicked) {
        { widget: Widget ->
            onDetailsClicked(widget)
        }
    }

    val shadowColor = if (isSystemInDarkTheme()) {
        Color.Black
    } else {
        Color.DarkGray
    }

    val pagerState = rememberPagerState(pageCount = { tray.widgets.size })

    val pageSize = remember {
        PageSize.Fixed(
            if (tray.widgets.first() is VideoWidget) {
                480.dp
            } else if (tray.widgets.first() is PeopleWidget) {
                120.dp
            } else {
                196.dp
            }
        )
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

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp, vertical = 0.dp),
            pageSpacing = 24.dp,
            pageSize = pageSize,
            key = { index -> tray.widgets[index].id }
        ) { pageIndex ->
            when (val widget = tray.widgets[pageIndex]) {
                is MovieWidget -> {
                    MovieWidgetComposable(
                        modifier = Modifier
                            .size(196.dp, 270.dp)
                            .graphicsLayer(pagerState, pageIndex)
                            .dropShadow(shadowColor, 24.dp, 16.dp),
                        widget = widget,
                        onDetailsClickedCallback,
                    )
                }
                is PeopleWidget -> {
                    PeopleWidgetComposable(
                        modifier = Modifier
                            .size(120.dp, 120.dp)
                            .graphicsLayer(pagerState, pageIndex),
                        widget = widget,
                        onDetailsClickedCallback,
                    )
                }
                is VideoWidget -> {
                    VideoWidgetComposable(
                        modifier = Modifier
                            .size(480.dp, 270.dp)
                            .graphicsLayer(pagerState, pageIndex)
                            .dropShadow(shadowColor, 24.dp, 16.dp),
                        widget = widget,
                        onDetailsClickedCallback,
                    )
                }
                is ImageWidget -> {
                    ImageWidgetComposable(
                        modifier = Modifier
                            .size(196.dp, 270.dp)
                            .graphicsLayer(pagerState, pageIndex)
                            .dropShadow(shadowColor, 24.dp, 16.dp),
                        widget = widget,
                        onDetailsClickedCallback,
                    )
                }
            }
        }
    }
}

fun Modifier.graphicsLayer(
    pagerState: PagerState,
    pageIndex: Int
): Modifier =
    graphicsLayer {
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

fun Modifier.dropShadow(shadowColor: Color, shapeRadius: Dp, shadowRadius: Dp): Modifier =
    dropShadow(
        shape = RoundedCornerShape(shapeRadius),
        shadow = Shadow(
            radius = shadowRadius,
            color = shadowColor
        ),
    )
