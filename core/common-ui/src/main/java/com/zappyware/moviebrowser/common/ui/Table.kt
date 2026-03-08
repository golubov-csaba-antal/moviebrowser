package com.zappyware.moviebrowser.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Table(
    modifier: Modifier,
    headers: List<String>,
    values: List<String>,
) {
    val indicatorWidth = 1.dp

    val tableBackgroundColor = if (isSystemInDarkTheme()) {
        Color(0x80151515)
    } else {
        Color(0x80FAFAFA)
    }
    val tableBorderColor = if (isSystemInDarkTheme()) {
        Color(0xF02A2A2A)
    } else {
        Color(0xF0F6F6F6)
    }
    val tableDividerColor = if (isSystemInDarkTheme()) {
        Color(0xF03F3F3F)
    } else {
        Color(0xF0F1F1F1)
    }

    Row(
        modifier = modifier
            .background(tableBackgroundColor, MaterialTheme.shapes.medium)
            .border(1.dp, tableBorderColor, MaterialTheme.shapes.medium)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.width(160.dp)
        ) {
            headers.forEach {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .drawBehind {
                            val strokeWidth = indicatorWidth.value * density
                            val lineX = size.width - strokeWidth / 2

                            drawLine(
                                tableDividerColor,
                                Offset(lineX, 0f),
                                Offset(lineX, size.height),
                                strokeWidth
                            )
                        }
                        .padding(horizontal = 0.dp, vertical = 6.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
        ) {
            values.forEach {
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}