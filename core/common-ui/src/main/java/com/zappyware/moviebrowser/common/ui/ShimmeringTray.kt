package com.zappyware.moviebrowser.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmeringTray() {
    Column {
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(48.dp)
                .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp)
                .shimmer(isLoading = true)
        )
        Box(
            modifier = Modifier.fillMaxWidth()
                .height(270.dp)
                .padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 8.dp)
                .shimmer(isLoading = true)
        )
    }
}
