package com.zappyware.moviebrowser.data.screen

import com.zappyware.moviebrowser.data.widget.Widget

data class DetailScreen(
    val widget: Widget,
    val attributes: Map<String,String>,
    val related: List<Widget>,
)
