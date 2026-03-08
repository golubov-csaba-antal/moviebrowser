@file:OptIn(ExperimentalMaterial3Api::class)

package com.zappyware.moviebrowser.page.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import com.zappyware.moviebrowser.common.ui.shimmer
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.page.DetailPageWidget
import com.zappyware.moviebrowser.page.detail.composable.MovieMeta

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel, movieId: String, mediaType: MediaType) {
    val pageWidget by viewModel.pageWidget.collectAsStateWithLifecycle()
    val isFavoriteState by viewModel.isFavorite.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchDetailWidget(movieId, mediaType)
    }

    pageWidget?.let {
        MovieDetailsScreenUI(
            it as DetailPageWidget,
            isFavoriteState,
            viewModel::onFavoriteClicked,
        )
    }
}

@Composable
fun MovieDetailsScreenUI(
    pageWidget: DetailPageWidget?,
    isFavoriteState: Boolean,
    onFavoriteClicked: (String, Boolean) -> Unit,
) {
    val windowInfo = LocalWindowInfo.current

    val screenWidth = windowInfo.containerDpSize.width
    val screenHeight = windowInfo.containerDpSize.height

    BottomSheetScaffold(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        sheetSwipeEnabled = true,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
        ),
        sheetPeekHeight = screenWidth,
        sheetContainerColor = MaterialTheme.colorScheme.surface,
        sheetDragHandle = null,
        sheetShadowElevation = 40.dp,
        sheetContent = {
            MovieMeta(
                pageWidget = pageWidget,
                isFavorite = isFavoriteState,
                modifier = Modifier.fillMaxWidth()
                    .height(screenHeight)
                    .padding(top = 32.dp, start = 24.dp, end = 24.dp),
                onFavoriteClicked = onFavoriteClicked
            )
        },
    ) { _ ->
        val sizeResolver = rememberConstraintsSizeResolver()
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(pageWidget?.posterPath)
                .size(sizeResolver)
                .build(),
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .shimmer(isLoading = pageWidget == null)
                .padding(top = 16.dp)
                .aspectRatio(2f/3f)
                .clip(RoundedCornerShape(32.dp, 32.dp))
                .then(sizeResolver),
            contentScale = ContentScale.FillWidth,
        )
    }
}
