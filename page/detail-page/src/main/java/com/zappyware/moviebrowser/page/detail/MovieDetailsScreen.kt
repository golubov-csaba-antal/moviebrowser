@file:OptIn(ExperimentalMaterial3Api::class)

package com.zappyware.moviebrowser.page.detail

import android.content.res.Configuration
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import com.zappyware.moviebrowser.common.ui.shimmer
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.page.detail.composable.MovieMeta

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel, movieId: String, mediaType: MediaType) {
    val movie by viewModel.movieWidget.collectAsStateWithLifecycle()
    val isFavoriteState by viewModel.isFavorite.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchDetailWidget(movieId, mediaType)
    }
    MovieDetailsScreenUI(
        movie,
        isFavoriteState,
        viewModel::onFavoriteClicked,
    )
}

@Composable
fun MovieDetailsScreenUI(
    movieWidget: MovieWidget?,
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
                movieWidget = movieWidget,
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
                .data(movieWidget?.coverUrl)
                .size(sizeResolver)
                .build(),
        )

        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
                .shimmer(isLoading = movieWidget == null)
                .padding(top = 16.dp)
                .aspectRatio(2f/3f)
                .clip(RoundedCornerShape(32.dp, 32.dp))
                .then(sizeResolver),
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
@Preview(
    name = "phone",
    device = "spec:width=360dp,height=640dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
fun MovieDetailsScreenUIPreview() {
    MovieDetailsScreenUI(
        movieWidget = MovieWidget(
            id = "123",
            title = "Example Movie",
            genres = "Action, Adventure, Sci-Fi",
            overview = "This is an overview of the example movie. It's full of action, adventure and sci-fi elements.",
            smallCoverUrl = "https://image.tmdb.org/t/p/w200/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
            coverUrl = "https://image.tmdb.org/t/p/original/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
            rating = 4.5f,
            mediaType = MediaType.MOVIE,
        ),
        isFavoriteState = true,
    ) { _, _ -> }
}
