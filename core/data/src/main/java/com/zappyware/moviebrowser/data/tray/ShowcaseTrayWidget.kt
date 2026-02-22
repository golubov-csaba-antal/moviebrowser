package com.zappyware.moviebrowser.data.tray

import com.zappyware.moviebrowser.data.widget.Widget

data class ShowcaseTrayWidget(
    override val id: Long,
    override val title: String,
    override val widget: Widget,
): SingleWidgetTray(id, title, widget)
