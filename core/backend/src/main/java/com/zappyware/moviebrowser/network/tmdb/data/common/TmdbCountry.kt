package com.zappyware.moviebrowser.network.tmdb.data.common

import com.google.gson.annotations.SerializedName

data class TmdbCountry(
    @SerializedName("iso_3166_1")
    val iso3166: String,
    @SerializedName("name")
    val name: String
)
