package com.zappyware.moviebrowser.data.tray

import com.zappyware.moviebrowser.data.common.Orientation
import com.zappyware.moviebrowser.data.widget.Widget

data class HorizontalPagerTrayWidget(
    override val id: String,
    override val title: String,
    override val widgets: List<Widget>,
    val orientation: Orientation = Orientation.Portrait,
): ScrollableTrayWidget(id, title, widgets)

val HorizontalPagerTrayWidget.trayItemWidth: Float get() =
    when (orientation) {
        Orientation.Portrait -> 198f
        Orientation.Landscape -> 270f
        Orientation.Circular -> 120f
    }

val HorizontalPagerTrayWidget.trayItemHeight: Float get() =
    when (orientation) {
        Orientation.Portrait -> 270f
        Orientation.Landscape -> 198f
        Orientation.Circular -> 120f
    }

