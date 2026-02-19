package com.zappyware.moviebrowser.page.landing

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.layout.WindowInsetsRulers
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zappyware.moviebrowser.data.Movie
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl
import com.zappyware.moviebrowser.network.tmdb.data.smallCoverUrl
import com.zappyware.moviebrowser.page.landing.composable.MovieListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.absoluteValue

@Composable
fun MovieListScreen(viewModel: MovieListViewModel, onDetailsClicked: (Movie) -> Unit) {
    val moviesListState = viewModel.movies.collectAsStateWithLifecycle(emptyList())
    MovieListScreenUI(
        moviesListState
    ) {
        onDetailsClicked(it)
    }
    viewModel.fetchMovies()
}

@Composable
fun MovieListScreenUI(
    movies: State<List<Movie>>,
    onDetailsClicked: (Movie) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .fitInside(WindowInsetsRulers.SafeDrawing.current)
    ) {
        Text(
            text = "Trending movies",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        )

        val pagerState = rememberPagerState(pageCount = { movies.value.size })
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp, vertical = 0.dp),
            pageSpacing = 24.dp,
            pageSize = PageSize.Fixed(196.dp),
        ) { pageIndex ->
            MovieListItem(
                modifier = Modifier.graphicsLayer {
                    val pageOffset = ((pagerState.currentPage - pageIndex) + pagerState.currentPageOffsetFraction).absoluteValue
                    val scale = lerp(
                        start = 0.94f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                    scaleX = scale
                    scaleY = scale
                    translationY = (1f - scaleY) * -270.dp.value
                }.dropShadow(
                    shape = RoundedCornerShape(24.dp),
                    shadow = Shadow(
                        radius = 16.dp,
                        color = if(isSystemInDarkTheme()) {
                            Color.Black
                        } else {
                            Color.DarkGray
                        }
                    ),
                ),
                movie = movies.value[pageIndex],
                onDetailsClicked,
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
fun MovieListScreenUIPreview() {
    MovieListScreenUI(
        MutableStateFlow(
            listOf(
                Movie(
                    id = 455476,
                    title = "Knights of the Zodiac",
                    genres = "Action, Sci-fi",
                    overview = "When a headstrong street orphan, Seiya, in search of his abducted sister unwittingly taps into hidden powers, he discovers he might be the only person alive who can protect a reincarnated goddess, sent to watch over humanity. Can he let his past go and embrace his destiny to become a Knight of the Zodiac?",
                    smallCoverUrl = smallCoverUrl("qW4crfED8mpNDadSmMdi7ZDzhXF.jpg"),
                    coverUrl = coverUrl("qW4crfED8mpNDadSmMdi7ZDzhXF.jpg"),
                    rating = 6.5f,
                    isFavorite = true,
                ),
                Movie(
                    id = 385687,
                    title = "Fast X",
                    genres = "Action",
                    overview = "Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path. Now, they confront the most lethal opponent they've ever faced: A terrifying threat emerging from the shadows of the past who's fueled by blood revenge, and who is determined to shatter this family and destroy everything—and everyone—that Dom loves, forever.",
                    smallCoverUrl = smallCoverUrl("fiVW06jE7z9YnO4trhaMEdclSiC.jpg"),
                    coverUrl = coverUrl("fiVW06jE7z9YnO4trhaMEdclSiC.jpg"),
                    rating = 7.4f,
                    isFavorite = false,
                ),
            )
        ).collectAsStateWithLifecycle(),
        onDetailsClicked = {},
    )
}
