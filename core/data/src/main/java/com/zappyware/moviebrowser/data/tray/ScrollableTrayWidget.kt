package com.zappyware.moviebrowser.data.tray

import com.zappyware.moviebrowser.data.widget.Widget

abstract class ScrollableTrayWidget(
    override val id: String,
    override val title: String,
    open val widgets: List<Widget>,
): TrayWidget(id, title)
