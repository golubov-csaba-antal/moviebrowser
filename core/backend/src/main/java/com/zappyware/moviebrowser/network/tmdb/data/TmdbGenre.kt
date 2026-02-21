package com.zappyware.moviebrowser.network.tmdb.data

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.GenreWidget

data class TmdbGenre(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,
)

fun TmdbGenre.toGenre(): GenreWidget = GenreWidget(
    id = id,
    title = name
)
