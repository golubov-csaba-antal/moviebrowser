package com.zappyware.moviebrowser.network.tmdb.data.entities

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.common.Language
import com.zappyware.moviebrowser.data.widget.VideoWidget
import java.util.Date

data class TmdbVideo(

    @SerializedName("id")
    val id: String,

    @SerializedName("title", ["name"])
    val title: String,

    @SerializedName("original_title", ["original_name"])
    val originalTitle: String?,

    @SerializedName("key")
    val key: String,

    @SerializedName("site")
    val site: String,

    @SerializedName("size")
    val size: Int,

    @SerializedName("type")
    val type: String,

    @SerializedName("official")
    val official: Boolean,

    @SerializedName("published_at")
    val published: Date,

    @SerializedName("iso_639_1")
    val iso639: String,

    @SerializedName("iso_3166_1")
    val iso3166: String,

)

fun TmdbVideo.toVideoWidget(): VideoWidget = VideoWidget(
    id = id,
    title = title,
    originalTitle = originalTitle.orEmpty(),
    key = key,
    site = site,
    size = size,
    type = type,
    official = official,
    published = published,
    language = Language(iso639, iso3166),
)
