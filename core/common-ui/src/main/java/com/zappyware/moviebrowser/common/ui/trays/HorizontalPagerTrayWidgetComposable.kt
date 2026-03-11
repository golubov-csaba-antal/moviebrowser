package com.zappyware.moviebrowser.common.ui.trays

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.LocalWidgetMapper
import com.zappyware.moviebrowser.common.ui.WidgetMapper
import com.zappyware.moviebrowser.common.ui.graphicsLayer
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.data.tray.trayItemHeight
import com.zappyware.moviebrowser.data.tray.trayItemWidth
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun HorizontalPagerTrayWidgetComposable(
    tray: HorizontalPagerTrayWidget,
    onDetailsClicked: (Widget) -> Unit,
    widgetMapper: WidgetMapper = LocalWidgetMapper.current,
) {
    val onDetailsClickedCallback = remember(onDetailsClicked) {
        { widget: Widget ->
            onDetailsClicked(widget)
        }
    }

    val pagerState = rememberPagerState(pageCount = { tray.widgets.size })

    val trayItemWidth = remember { tray.trayItemWidth }
    val trayItemHeight = remember { tray.trayItemHeight }

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
            pageSize = PageSize.Fixed(trayItemWidth.dp),
            key = { index -> tray.widgets[index].id }
        ) { pageIndex ->
            val widget = tray.widgets[pageIndex]
            widgetMapper.Map(
                widget = widget,
                orientation = tray.orientation,
                onDetailsClicked = onDetailsClickedCallback,
                modifier = Modifier
                    .size(trayItemWidth.dp, trayItemHeight.dp)
                    .graphicsLayer(pagerState, pageIndex, trayItemHeight)
            )
        }
    }
}
