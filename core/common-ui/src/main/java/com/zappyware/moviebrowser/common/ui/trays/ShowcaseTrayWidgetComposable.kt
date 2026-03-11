package com.zappyware.moviebrowser.common.ui.trays

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.widgets.MovieWidgetFullScreenComposable
import com.zappyware.moviebrowser.data.tray.ShowcaseTrayWidget
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun ShowcaseTrayWidgetComposable(
    tray: ShowcaseTrayWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val colorProvider = LocalColorProvider.current

    Column {
        Text(
            text = tray.title,
            style = MaterialTheme.typography.bodyLarge,
            color = colorProvider.textColorDark,
            modifier = Modifier
                .height(48.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        when (val widget = tray.widget) {
            is MovieWidget -> {
                MovieWidgetFullScreenComposable(
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(2f / 3f),
                    widget = widget,
                    onDetailsClicked = onDetailsClicked,
                )
            }
        }
    }
}
