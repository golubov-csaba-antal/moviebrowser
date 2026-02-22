package com.zappyware.moviebrowser.network.tmdb.util

import com.zappyware.moviebrowser.network.tmdb.data.TmdbInterval
import retrofit2.Converter

class TmdbIntervalConverter: Converter<TmdbInterval, String?> {
    override fun convert(value: TmdbInterval?): String? = value?.name?.lowercase()
}
