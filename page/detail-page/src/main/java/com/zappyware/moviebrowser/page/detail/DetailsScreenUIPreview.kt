package com.zappyware.moviebrowser.page.detail

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zappyware.moviebrowser.data.common.ContentType
import com.zappyware.moviebrowser.data.page.DetailPageWidget

@Composable
@Preview(
    name = "phone",
    device = "spec:width=360dp,height=640dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
fun DetailsScreenUIPreview() {
    DetailsScreenUI(
        pageWidget = DetailPageWidget(
            adult = false,
            backdropPath = null,
            created = emptyList(),
            episodeRunTime = emptyList(),
            firstAirDate = null,
            genres = "Action, Adventure & Sci-Fi",
            homepage = "",
            id = "123",
            isInProduction = false,
            languages = emptyList(),
            lastAirDate = null,
            lastEpisodeToAir = null,
            title = "Example Movie",
            nextEpisodeToAir = null,
            networks = emptyList(),
            episodesCount = 0,
            seasonsCount = 0,
            originCountry = emptyList(),
            originalLanguage = "",
            originalTitle = "",
            overview = "This is an overview of the example movie. It's full of action, adventure and sci-fi elements.",
            popularity = 0f,
            posterPath = "/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
            productionCompanies = emptyList(),
            productionCountries = emptyList(),
            seasons = emptyList(),
            dubs = emptyList(),
            status = "",
            tagline = "",
            type = ContentType.Scripted,
            voteAverage = 4.5f,
            voteCount = 6,
            isVideo = false,
            videos = emptyList(),
            images = emptyList(),
        ),
        isFavoriteState = true,
    ) { _, _ -> }
}
