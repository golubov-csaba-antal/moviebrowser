package com.zappyware.moviebrowser.data.tray

import com.zappyware.moviebrowser.data.widget.Widget

abstract class TrayWidget(
    override val id: Long,
    open val title: String,
): Widget(id)
