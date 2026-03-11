@file:OptIn(ExperimentalMaterial3Api::class)

package com.zappyware.moviebrowser.page.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
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
import com.zappyware.moviebrowser.page.detail.composable.MovieCreator
import com.zappyware.moviebrowser.page.detail.composable.MovieGenre
import com.zappyware.moviebrowser.page.detail.composable.MovieMeta
import com.zappyware.moviebrowser.page.detail.composable.MovieOverview
import com.zappyware.moviebrowser.page.detail.composable.MovieRelatedImages
import com.zappyware.moviebrowser.page.detail.composable.MovieRelatedVideos
import com.zappyware.moviebrowser.page.detail.composable.MovieTagline
import com.zappyware.moviebrowser.page.detail.composable.MovieTitle
import com.zappyware.moviebrowser.page.detail.composable.Seasons

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
    val density = LocalDensity.current
    val scaffoldState = rememberBottomSheetScaffoldState()

    val screenWidth = windowInfo.containerDpSize.width
    val screenHeight = windowInfo.containerDpSize.height

    // Calculate the expansion progress (0.0 when fully expanded, 1.0 at peek height)
    val sheetOffset = try {
        scaffoldState.bottomSheetState.requireOffset()
    } catch (_: Exception) {
        // Default to peek position before layout is calculated
        with(density) { (screenHeight - screenWidth).toPx() }
    }

    val peekOffsetPx = with(density) { (screenHeight - screenWidth).toPx() }
    val progress = (sheetOffset / peekOffsetPx).coerceIn(0f, 1f)

    // Interpolate outer padding and corners based on scroll progress
    val horizontalPadding = (20 * progress).dp
    val cornerRadius = (24 * progress).dp
    
    // Interpolate inner padding to counteract the outer padding change.
    // When progress = 1 (peeked), inner padding is 8dp. 
    // When progress = 0 (expanded), inner padding is 24dp (8dp base + 16dp extra).
    // This keeps the total offset from screen edge constant at 16 + 8 = 24dp.
    val innerHorizontalPadding = 40.dp - horizontalPadding

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier, // Padding removed from here to allow full-width expansion
        sheetSwipeEnabled = true,
        sheetShape = RectangleShape, // Shape is handled by the inner Surface
        sheetPeekHeight = screenWidth,
        sheetContainerColor = Color.Transparent, // Make container transparent to show dynamic Surface
        sheetDragHandle = null,
        sheetShadowElevation = 0.dp,
        sheetContent = {
            Surface(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(
                    topStart = cornerRadius,
                    topEnd = cornerRadius,
                ),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 40.dp,
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = screenHeight)
                        .verticalScroll(scrollState)
                        .padding(vertical = 32.dp)
                ) {
                    MovieTitle(
                        pageWidget = pageWidget,
                        isFavorite = isFavoriteState,
                        onFavoriteClicked = onFavoriteClicked,
                        horizontalPadding = innerHorizontalPadding
                    )
                    MovieGenre(
                        pageWidget = pageWidget,
                        horizontalPadding = innerHorizontalPadding
                    )
                    MovieTagline(
                        pageWidget = pageWidget,
                        horizontalPadding = innerHorizontalPadding
                    )
                    MovieOverview(
                        pageWidget = pageWidget,
                        horizontalPadding = innerHorizontalPadding
                    )
                    MovieMeta(
                        pageWidget = pageWidget,
                        horizontalPadding = innerHorizontalPadding
                    )
                    MovieCreator(
                        pageWidget = pageWidget,
                    )
                    Seasons(
                        pageWidget = pageWidget,
                    )
                    MovieRelatedImages(
                        pageWidget = pageWidget,
                    )
                    MovieRelatedVideos(
                        pageWidget = pageWidget,
                    )
                }
            }
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp) // Maintain consistent body padding
                .shimmer(isLoading = pageWidget == null)
                .padding(top = 16.dp)
                .aspectRatio(2f / 3f)
                .clip(RoundedCornerShape(32.dp, 32.dp))
                .then(sizeResolver),
            contentScale = ContentScale.FillWidth,
        )
    }
}
