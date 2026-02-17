package com.zappyware.moviebrowser.network.tmdb.data

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.Genre

data class TmdbGenre(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,
)

fun TmdbGenre.toGenre(): Genre = Genre(
    id = id,
    title = name
)
