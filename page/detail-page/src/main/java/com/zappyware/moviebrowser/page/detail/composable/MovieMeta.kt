package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.StringFormatter
import com.zappyware.moviebrowser.common.ui.Table
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MovieMeta(
    pageWidget: DetailPageWidget?,
    horizontalPadding: Dp = 16.dp,
) {
    pageWidget?.run {
        val (headers, values) = remember(this) {
            val h = mutableListOf<String>()
            val v = mutableListOf<String>()

            fun addTableContent(header: String, value: String?) {
                if (!value.isNullOrBlank()) {
                    h.add(header)
                    v.add(value)
                }
            }

            addTableContent(
                "Status",
                status.takeIf { it.isNotEmpty() },
            )
            addTableContent(
                "Seasons count",
                seasonsCount.takeIf { it != 0 }?.let { "$it" }
            )
            addTableContent(
                "Episodes count",
                episodesCount.takeIf { it != 0 }?.let { "$it" }
            )
            addTableContent(
                "First aired",
                firstAirDate ?.let { DateFormat.format(it) },
            )
            addTableContent(
                "Last aired",
                lastAirDate?.let { DateFormat.format(it) },
            )
            addTableContent(
                "Vote avg.",
                voteAverage?.let { TextFormat.format(it) },
            )
            addTableContent(
                "Popularity",
                popularity?.let { TextFormat.format(it) },
            )
            h to v
        }

        Table(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding, vertical = 16.dp),
            headers = headers,
            values = values
        )
    }
}

private val DateFormat = SimpleDateFormat("yyyy. MMM dd.", Locale.getDefault())
private val TextFormat = StringFormatter("%.1f", Locale.getDefault())
