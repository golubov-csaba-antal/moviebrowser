package com.zappyware.moviebrowser.network.tmdb.data.entities

import com.google.gson.annotations.SerializedName
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.common.toGender
import com.zappyware.moviebrowser.data.widget.PeopleWidget
import com.zappyware.moviebrowser.network.tmdb.data.coverUrl
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbMediaType
import com.zappyware.moviebrowser.network.tmdb.data.enums.toMediaType

data class TmdbPeople(

    @SerializedName("adult")
    val isAdult: Boolean?,

    @SerializedName("id")
    val id: String,

    @SerializedName("credit_id")
    val creditId: String?,

    @SerializedName("name")
    val name: String,

    @SerializedName("original_name")
    val originalName: String,

    @SerializedName("media_type")
    val mediaType: TmdbMediaType?,

    @SerializedName("popularity")
    val popularity: Float,

    @SerializedName("gender")
    val gender: Int,

    @SerializedName("known_for_department")
    val department: String?,

    @SerializedName("profile_path")
    val profilePath: String?,

    @SerializedName("known_for")
    val relatedContents: List<TmdbMovie>?,
)

fun TmdbPeople.toPeopleWidget(): PeopleWidget = PeopleWidget(
    isAdult = isAdult,
    id = id,
    creditId = creditId,
    name = name,
    originalName = originalName,
    mediaType = mediaType?.toMediaType() ?: MediaType.PERSON,
    popularity = popularity,
    gender = gender.toGender(),
    department = department,
    profilePath = profilePath,
    relatedContents = emptyList(),
)

suspend fun TmdbPeople.toPeopleWidget(fetchGenresCall: suspend (TmdbMediaType?, List<String>?) -> List<String>): PeopleWidget = PeopleWidget(
    isAdult = isAdult,
    id = id,
    creditId = creditId,
    name = name,
    originalName = originalName,
    mediaType = mediaType?.toMediaType() ?: MediaType.PERSON,
    popularity = popularity,
    gender = gender.toGender(),
    department = department,
    profilePath = profilePath?.let { coverUrl(it) },
    relatedContents = relatedContents?.map { it.toMovie(fetchGenresCall(it.mediaType, it.genreIds)) } ?: emptyList(),
)
