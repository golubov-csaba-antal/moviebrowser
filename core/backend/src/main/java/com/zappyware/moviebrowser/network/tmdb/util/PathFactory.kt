package com.zappyware.moviebrowser.network.tmdb.util

import com.zappyware.moviebrowser.network.tmdb.data.TmdbInterval
import com.zappyware.moviebrowser.network.tmdb.data.TmdbMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class PathFactory: Converter.Factory() {

    override fun stringConverter(
        type: Type?,
        annotations: Array<out Annotation?>?,
        retrofit: Retrofit?
    ): Converter<*, String?>? {
        return when(type) {
            TmdbMediaType::class.java -> TmdbMediaTypeConverter()
            TmdbInterval::class.java -> TmdbIntervalConverter()
            else -> null
        }
    }
}
