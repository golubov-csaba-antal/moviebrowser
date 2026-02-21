package com.zappyware.moviebrowser.data

abstract class TrayWidget(
    override val id: Long,
    open val title: String,
    open val widgets: List<Widget>,
): Widget(id)
