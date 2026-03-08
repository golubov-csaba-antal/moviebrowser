package com.zappyware.moviebrowser.network.tmdb.data.page

import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.page.PageWidget

abstract class TmdbPage {
    abstract fun getGenres(): List<String>?
    abstract fun toPageWidget(mediaType: MediaType, genres: List<String>): PageWidget
}
