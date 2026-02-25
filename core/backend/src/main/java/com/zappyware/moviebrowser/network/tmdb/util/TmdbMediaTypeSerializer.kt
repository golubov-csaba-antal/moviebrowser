package com.zappyware.moviebrowser.network.tmdb.util

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.zappyware.moviebrowser.network.tmdb.data.TmdbMediaType
import com.zappyware.moviebrowser.util.EnumSerializer
import java.lang.reflect.Type

class TmdbMediaTypeSerializer: EnumSerializer<TmdbMediaType>() {

    override fun serialize(
        src: TmdbMediaType?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src?.mediaType?.lowercase())
    }
}
