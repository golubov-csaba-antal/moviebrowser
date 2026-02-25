package com.zappyware.moviebrowser.network.tmdb.util

import com.zappyware.moviebrowser.network.tmdb.data.TmdbMediaType
import com.zappyware.moviebrowser.util.EnumDeserializer

class TmdbMediaTypeDeserializer: EnumDeserializer<TmdbMediaType>() {

    override fun parseEnum(
        enumClass: Class<TmdbMediaType>,
        jsonString: String
    ): TmdbMediaType? {
        return TmdbMediaType.entries.find { it.mediaType == jsonString }
    }
}
