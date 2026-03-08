package com.zappyware.moviebrowser.data.tray

import com.zappyware.moviebrowser.data.widget.Widget

abstract class TrayWidget(
    override val id: String,
    open val title: String,
): Widget(id)
