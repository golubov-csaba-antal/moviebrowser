package com.zappyware.moviebrowser.network.tmdb.data.entities

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.common.toGender
import com.zappyware.moviebrowser.data.widget.CreatorWidget

data class TmdbCreator(

    @SerializedName("id")
    val id: String,

    @SerializedName("credit_id")
    val creditId: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("gender")
    val gender: Int,

    @SerializedName("profile_path")
    val profilePath: String?,
)

fun TmdbCreator.toCreatorWidget(): CreatorWidget = CreatorWidget(
    id = id,
    creditId = creditId,
    name = name,
    originalName = originalName,
    gender = gender.toGender(),
    profilePath = profilePath,
)
