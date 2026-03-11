package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.dropShadow
import com.zappyware.moviebrowser.common.ui.withShadow
import com.zappyware.moviebrowser.data.widget.VideoWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun VideoWidgetComposable(
    modifier: Modifier,
    widget: VideoWidget,
    castShadow: Boolean = false,
    onDetailsClicked: (Widget) -> Unit,
) {
    val colorProvider = LocalColorProvider.current

    Column(
        modifier = modifier
            .dropShadow(castShadow, colorProvider.shadowColor, 24.dp, 16.dp)
            .zIndex(1.0f)
            .clip(RoundedCornerShape(24.dp))
            .background(colorProvider.imageBackgroundColor)
            .padding(16.dp)
            .clickable {
                onDetailsClicked(widget)
            },
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = widget.title,
            style = MaterialTheme.typography.labelLarge.withShadow(castShadow = true, colorProvider.shadowColor),
            color = colorProvider.textColorLight,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${widget.type}, ${widget.site}",
            style = MaterialTheme.typography.labelSmall.withShadow(castShadow = true, colorProvider.shadowColor),
            color = colorProvider.textColorLight,
        )
    }
}
