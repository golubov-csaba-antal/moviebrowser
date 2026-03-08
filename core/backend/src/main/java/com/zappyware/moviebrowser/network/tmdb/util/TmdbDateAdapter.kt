package com.zappyware.moviebrowser.network.tmdb.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TmdbDateAdapter : JsonDeserializer<Date?> {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Date? {
        if (json.isJsonNull || json.asString.isNullOrEmpty()) {
            return null
        }
        
        return try {
            dateFormat.parse(json.asString)
        } catch (e: ParseException) {
            null
        }
    }
}
