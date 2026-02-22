package com.zappyware.moviebrowser.data.tray

import com.zappyware.moviebrowser.data.widget.Widget

abstract class SingleWidgetTray(
    override val id: Long,
    override val title: String,
    open val widget: Widget,
): TrayWidget(id, title)
