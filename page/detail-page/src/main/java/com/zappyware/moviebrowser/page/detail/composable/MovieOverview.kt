package com.zappyware.moviebrowser.page.detail.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.data.page.DetailPageWidget

@Composable
fun MovieOverview(
    pageWidget: DetailPageWidget?
) {
    pageWidget?.let {
        pageWidget.tagline.takeIf { it.isNotEmpty() }?.let { tagline ->
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                text = tagline,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
        }
        pageWidget.overview.takeIf { it.isNotEmpty() }?.let { overview ->
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                text = overview,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}
