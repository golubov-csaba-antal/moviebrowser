package com.zappyware.moviebrowser.network.tmdb.util

import com.zappyware.moviebrowser.network.tmdb.data.TmdbMediaType
import retrofit2.Converter

class TmdbMediaTypeConverter: Converter<TmdbMediaType, String?> {
    override fun convert(value: TmdbMediaType?): String? = value?.mediaType?.lowercase()
}
