package com.zappyware.moviebrowser.page.landing

import androidx.compose.foundation.gestures.snapping.SnapLayoutInfoProvider
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zappyware.moviebrowser.common.ui.LocalFavoriteProvider
import com.zappyware.moviebrowser.common.ui.LocalTrayMapper
import com.zappyware.moviebrowser.common.ui.ShimmeringTray
import com.zappyware.moviebrowser.common.ui.TrayMapper
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun LandingScreen(
    viewModel: LandingViewModel,
    onDetailsClicked: (Widget) -> Unit,
    trayMapper: TrayMapper = LocalTrayMapper.current
) {
    val trays by viewModel.trays.collectAsStateWithLifecycle(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchLandingTrays()
    }

    val lazyState = rememberLazyListState()
    val snappingLayout = remember(lazyState) { SnapLayoutInfoProvider(lazyState, SnapPosition.Start) }
    val flingBehavior = rememberSnapFlingBehavior(snappingLayout)

    if (trays.isEmpty()) {
        Column(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            repeat(times = 3) {
                ShimmeringTray()
            }
        }
    } else {
        CompositionLocalProvider(LocalFavoriteProvider provides viewModel) {
            LazyColumn(
                state = lazyState,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(0.dp, vertical = 16.dp),
                flingBehavior = flingBehavior,
            ) {
                items(items = trays, key = { item -> item.id }) { trayWidget ->
                    trayMapper.Map(trayWidget, onDetailsClicked)
                }
            }
        }
    }
}
