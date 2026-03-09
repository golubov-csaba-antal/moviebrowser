package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.zappyware.moviebrowser.common.ui.trays.HorizontalPagerTrayWidgetComposable
import com.zappyware.moviebrowser.data.common.Orientation
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget

@Composable
fun Seasons(
    pageWidget: DetailPageWidget?,
) {
    if (pageWidget == null) return

    val tray = remember(pageWidget.seasons) {
        if (pageWidget.seasons.isEmpty()) {
            return@remember null
        } else {
            HorizontalPagerTrayWidget(
                id = "seasons",
                title = "Seasons",
                widgets = pageWidget.seasons,
                orientation = Orientation.Landscape,
            )
        }
    }

    tray?.let {
        HorizontalPagerTrayWidgetComposable(
            tray = it,
            onDetailsClicked = { },
        )
    }
}
