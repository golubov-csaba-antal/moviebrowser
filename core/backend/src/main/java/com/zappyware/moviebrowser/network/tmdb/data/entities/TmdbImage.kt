package com.zappyware.moviebrowser.network.tmdb.data.entities

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.common.Language
import com.zappyware.moviebrowser.data.widget.ImageWidget
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl

data class TmdbImage(

    @SerializedName("category")
    val category: String,

    @SerializedName("aspect_ratio")
    val aspectRatio: Float,

    @SerializedName("width")
    val width: Int,

    @SerializedName("size")
    val height: Int,

    @SerializedName("iso_3166_1")
    val iso3166: String,

    @SerializedName("iso_639_1")
    val iso639: String,

    @SerializedName("file_path")
    val imagePath: String?,

    @SerializedName("vote_average")
    val voteAverage: Float,

    @SerializedName("vote_count")
    val voteCount: Int,

)

fun TmdbImage.toImageWidget(): ImageWidget = ImageWidget(
    category = category,
    aspectRatio = aspectRatio,
    width = width,
    height = height,
    language = Language(iso639, iso3166),
    imagePath = coverUrl(imagePath!!),
    voteAverage = voteAverage,
    voteCount = voteCount,
)
