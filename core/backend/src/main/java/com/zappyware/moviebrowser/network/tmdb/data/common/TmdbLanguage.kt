package com.zappyware.moviebrowser.network.tmdb.data.common

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.common.Language

data class TmdbLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
)

fun TmdbLanguage.toLanguage() = Language(
    code = iso6391,
    name = name,
)
