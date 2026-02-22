package com.zappyware.moviebrowser.data.tray

import com.zappyware.moviebrowser.data.widget.Widget

data class HorizontalPagerTrayWidget(
    override val id: Long,
    override val title: String,
    override val widgets: List<Widget>,
): ScrollableTrayWidget(id, title, widgets)
