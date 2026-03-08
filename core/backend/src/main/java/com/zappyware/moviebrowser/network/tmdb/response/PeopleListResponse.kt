package com.zappyware.moviebrowser.network.tmdb.response

import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbPeople

data class PeopleListResponse(
    val results: List<TmdbPeople>
)
