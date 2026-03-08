package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.zappyware.moviebrowser.data.widget.VideoWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun VideoWidgetComposable(
    modifier: Modifier,
    widget: VideoWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val backgroundColor = if (isSystemInDarkTheme()) {
        Color.DarkGray
    } else {
        Color.LightGray
    }

    Column(
        modifier = modifier
            .zIndex(1.0f)
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .padding(16.dp)
            .clickable {
                onDetailsClicked(widget)
            },
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = widget.title,
            style = MaterialTheme.typography.labelLarge.copy(
                shadow = Shadow(
                    color = Color.Black, offset = Offset(5f, 5f), blurRadius = 5f
                )
            ),
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${widget.type}, ${widget.site}",
            style = MaterialTheme.typography.labelSmall.copy(
                shadow = Shadow(
                    color = Color.Black, offset = Offset(5f, 5f), blurRadius = 5f
                )
            ),
            color = Color.White,
        )
    }
}
