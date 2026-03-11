package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.zappyware.moviebrowser.common.ui.trays.HorizontalPagerTrayWidgetComposable
import com.zappyware.moviebrowser.data.common.Orientation
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget

@Composable
fun MovieRelatedImages(
    pageWidget: DetailPageWidget?,
) {
    if (null == pageWidget) return

    val tray = remember(pageWidget.images) {
        val filteredImages = pageWidget.images.filter { it.category == "posters" }
        if (filteredImages.isNotEmpty()) {
            HorizontalPagerTrayWidget(
                id = "related_images_${pageWidget.id}",
                title = "Related images",
                widgets = filteredImages,
                orientation = Orientation.Portrait,
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
