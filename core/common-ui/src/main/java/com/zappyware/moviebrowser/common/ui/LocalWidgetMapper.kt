package com.zappyware.moviebrowser.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.zappyware.moviebrowser.common.ui.mapper.DefaultWidgetMapper
import com.zappyware.moviebrowser.data.common.Orientation
import com.zappyware.moviebrowser.data.widget.Widget

interface WidgetMapper {
    @Composable
    fun Map(
        widget: Widget,
        orientation: Orientation,
        onDetailsClicked: (Widget) -> Unit,
        modifier: Modifier
    )
}

val LocalWidgetMapper = staticCompositionLocalOf<WidgetMapper> {
    DefaultWidgetMapper()
}
