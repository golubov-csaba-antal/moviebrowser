package com.zappyware.moviebrowser.network.tmdb.data.entities

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.widget.GenreWidget

data class TmdbGenre(

    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,
)

fun TmdbGenre.toGenre(): GenreWidget = GenreWidget(
    id = id,
    title = name
)
