package com.zappyware.moviebrowser.page.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.WindowInsetsRulers
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import coil3.request.ImageRequest
import com.zappyware.moviebrowser.data.Movie

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel, movieId: Long) {
    val rememberMovie = remember { mutableStateOf<Movie?>(null) }
    MovieDetailsScreenUI(
        rememberMovie.value,
        viewModel::onFavoriteClicked,
    )
    viewModel.getMovieById(movieId) {
        rememberMovie.value = it
    }
}

@Composable
fun MovieDetailsScreenUI(
    movie: Movie?,
    onFavoriteClicked: (Long,Boolean) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fitInside(WindowInsetsRulers.SafeDrawing.current)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (movie == null) {
            CircularProgressIndicator(
                modifier = Modifier.padding(all = 16.dp)
            )
        } else {
            val sizeResolver = rememberConstraintsSizeResolver()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalPlatformContext.current)
                    .data(movie.coverUrl)
                    .size(sizeResolver)
                    .build(),
            )

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
                    .aspectRatio(2f/3f)
                    .then(sizeResolver),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                painter = if (movie.isFavorite) {
                    painterResource(id = android.R.drawable.btn_star_big_on)
                } else {
                    painterResource(id = android.R.drawable.btn_star_big_off)
                },
                contentDescription = null,
                modifier = Modifier.clickable {
                    onFavoriteClicked(movie.id, !movie.isFavorite)
                },
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.overview ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
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
        movie = Movie(
            id = 123L,
            title = "Example Movie",
            genres = "Action, Adventure, Sci-Fi",
            overview = "This is an overview of the example movie. It's full of action, adventure and sci-fi elements.",
            smallCoverUrl = "https://image.tmdb.org/t/p/w200/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
            coverUrl = "https://image.tmdb.org/t/p/original/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
            rating = 4.5f,
            isFavorite = false,
        ),
        onFavoriteClicked = { _, _ -> },
    )
}
