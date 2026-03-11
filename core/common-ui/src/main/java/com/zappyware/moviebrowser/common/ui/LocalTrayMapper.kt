package com.zappyware.moviebrowser.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import com.zappyware.moviebrowser.common.ui.mapper.DefaultTrayMapper
import com.zappyware.moviebrowser.data.tray.TrayWidget
import com.zappyware.moviebrowser.data.widget.Widget

interface TrayMapper {
    @Composable
    fun Map(tray: TrayWidget, onDetailsClicked: (Widget) -> Unit)
}

val LocalTrayMapper = staticCompositionLocalOf<TrayMapper> {
    DefaultTrayMapper()
}
