package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.runtime.Composable
import com.zappyware.moviebrowser.common.ui.trays.HorizontalPagerTrayWidgetComposable
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget

@Composable
fun MovieCreator(
    pageWidget: DetailPageWidget?,
) {
    pageWidget?.created.orEmpty().takeIf { it.isNotEmpty() }?.let {
        HorizontalPagerTrayWidgetComposable(
            tray = HorizontalPagerTrayWidget(
                id = "1",
                title = "Creator",
                widgets = it,
            ),
            onDetailsClicked = { },
        )
    }
}
