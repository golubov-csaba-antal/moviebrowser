package com.zappyware.moviebrowser.network.tmdb.data.common

import com.google.gson.annotations.SerializedName

data class TmdbCompany(
    @SerializedName("id")
    val id: String,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)
