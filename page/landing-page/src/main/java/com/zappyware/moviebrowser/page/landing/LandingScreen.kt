package com.zappyware.moviebrowser.page.landing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zappyware.moviebrowser.common.ui.trays.HorizontalPagerTrayWidgetComposable
import com.zappyware.moviebrowser.data.Widget

@Composable
fun LandingScreen(viewModel: LandingViewModel, onDetailsClicked: (Widget) -> Unit) {
    val traysWithState = viewModel.trays.collectAsStateWithLifecycle(emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(count = traysWithState.value.size, key = { index -> traysWithState.value[index].id * 10L + index }) { index ->
            HorizontalPagerTrayWidgetComposable(
                tray = traysWithState.value[index],
                onDetailsClicked = onDetailsClicked,
            )
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchTrendingMovies()
    }
}
