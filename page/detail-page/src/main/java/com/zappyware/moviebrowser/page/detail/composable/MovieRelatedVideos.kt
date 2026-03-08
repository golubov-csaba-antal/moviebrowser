package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.zappyware.moviebrowser.common.ui.trays.HorizontalPagerTrayWidgetComposable
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget

@Composable
fun MovieRelatedVideos(
    pageWidget: DetailPageWidget?,
) {
    if (pageWidget == null) return

    val tray = remember(pageWidget.videos) {
        if (pageWidget.videos.isNotEmpty()) {
            HorizontalPagerTrayWidget(
                id = "related_videos_${pageWidget.id}",
                title = "Related videos",
                widgets = pageWidget.videos,
            )
        } else null
    }

    tray?.let {
        HorizontalPagerTrayWidgetComposable(
            tray = it,
            onDetailsClicked = { },
        )
    }
}
