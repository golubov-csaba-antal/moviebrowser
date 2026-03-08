package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.Table
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MovieMeta(
    pageWidget: DetailPageWidget?,
) {
    pageWidget?.let { widget ->
        val (headers, values) = remember(widget) {
            val h = mutableListOf<String>()
            val v = mutableListOf<String>()
            val dateFormat = SimpleDateFormat("yyyy. MMM. dd.", Locale.getDefault())

            widget.status
                .takeIf { it.isNotEmpty() }
                ?.let {
                    h.add("Status")
                    v.add(it)
                }
            widget.seasonsCount
                ?.takeIf { it != 0 }
                ?.let {
                    h.add("Seasons count")
                    v.add("$it")
                }
            widget.episodesCount
                ?.takeIf { it != 0 }
                ?.let {
                    h.add("Episodes count")
                    v.add("$it")
                }
            widget.firstAirDate
                ?.let {
                    h.add("First aired")
                    v.add(dateFormat.format(it))
                }
            widget.lastAirDate
                ?.let {
                    h.add("Last aired")
                    v.add(dateFormat.format(it))
                }
            widget.voteAverage
                ?.let {
                    h.add("Vote avg.")
                    v.add(String.format(Locale.getDefault(), "%.1f", it))
                }
            widget.popularity
                ?.let {
                    h.add("Popularity")
                    v.add(String.format(Locale.getDefault(), "%.0f", it))
                }
            h to v
        }

        Table(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            headers = headers,
            values = values
        )
    }
}
