package com.zappyware.moviebrowser.util

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

abstract class EnumDeserializer<T : Enum<T>> : JsonDeserializer<T> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): T? {
        val jsonString = json?.asString
        return if (jsonString.isNullOrEmpty()) {
            null
        } else {
            val enumClass = typeOfT as? Class<T>
            enumClass?.let { parseEnum(it, jsonString) }
        }
    }

    open fun parseEnum(enumClass: Class<T>, jsonString: String): T? = enumClass.enumConstants?.first {
            enumValue -> enumValue.name.equals(jsonString, true)
    }
}
