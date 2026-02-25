package com.zappyware.moviebrowser.util

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

abstract class EnumSerializer<T : Enum<T>> : JsonSerializer<T> {

    override fun serialize(
        src: T?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement = JsonPrimitive(src?.name?.lowercase())

}
