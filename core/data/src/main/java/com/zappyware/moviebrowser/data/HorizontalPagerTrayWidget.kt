package com.zappyware.moviebrowser.data

data class HorizontalPagerTrayWidget(
    override val id: Long = 1L,
    override val title: String,
    override val widgets: List<MovieWidget>,
): TrayWidget(id, title, widgets)
