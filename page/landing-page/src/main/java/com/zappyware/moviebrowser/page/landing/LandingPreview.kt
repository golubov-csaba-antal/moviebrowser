package com.zappyware.moviebrowser.page.landing

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.zappyware.moviebrowser.common.ui.trays.HorizontalPagerTrayWidgetComposable
import com.zappyware.moviebrowser.data.MovieWidget
import com.zappyware.moviebrowser.data.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl
import com.zappyware.moviebrowser.network.tmdb.data.smallCoverUrl

@Composable
@Preview(
    name = "phone",
    device = "spec:width=420dp,height=900dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
fun LandingPreview() {
    val previewMovies = remember {
        mutableStateOf(
            listOf(
                MovieWidget(
                    id = 455476,
                    title = "Knights of the Zodiac",
                    genres = "Action, Sci-fi",
                    overview = "When a headstrong street orphan, Seiya, in search of his abducted sister unwittingly taps into hidden powers, he discovers he might be the only person alive who can protect a reincarnated goddess, sent to watch over humanity. Can he let his past go and embrace his destiny to become a Knight of the Zodiac?",
                    smallCoverUrl = smallCoverUrl("qW4crfED8mpNDadSmMdi7ZDzhXF.jpg"),
                    coverUrl = coverUrl("qW4crfED8mpNDadSmMdi7ZDzhXF.jpg"),
                    rating = 6.5f,
                    isFavorite = true,
                ),
                MovieWidget(
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
        )
    }
    val previewTray = remember {
        HorizontalPagerTrayWidget(
            title = "Trending movies",
            widgets = previewMovies.value
        )
    }
    HorizontalPagerTrayWidgetComposable(
        tray = previewTray,
        onDetailsClicked = {},
    )
}
