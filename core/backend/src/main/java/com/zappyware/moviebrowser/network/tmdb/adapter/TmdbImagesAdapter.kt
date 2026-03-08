package com.zappyware.moviebrowser.network.tmdb.adapter

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbImage

class TmdbImagesAdapter : TypeAdapter<List<TmdbImage>>() {

    private val gson = Gson()
    private val imageType = object : TypeToken<TmdbImage>() {}.type

    override fun write(out: JsonWriter, value: List<TmdbImage>?) {
        if (value == null) {
            out.nullValue()
            return
        }
        
        // Group images by category to reconstruct the TMDB JSON structure
        val grouped = value.groupBy { it.category }
        out.beginObject()
        grouped.forEach { (category, list) ->
            out.name(category)
            out.beginArray()
            list.forEach { image ->
                gson.toJson(image, imageType, out)
            }
            out.endArray()
        }
        out.endObject()
    }

    override fun read(reader: JsonReader): List<TmdbImage> {
        val images = mutableListOf<TmdbImage>()

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return emptyList()
        }

        reader.beginObject()
        while (reader.hasNext()) {
            // The key name (e.g., "backdrops", "posters") is our category
            val category = reader.nextName() 
            
            if (reader.peek() == JsonToken.BEGIN_ARRAY) {
                reader.beginArray()
                while (reader.hasNext()) {
                    // We read each image as a JsonObject so we can inject the 'category' key
                    // that TMDB doesn't provide inside the image object itself.
                    val jsonObject = gson.fromJson<JsonObject>(reader, JsonObject::class.java)
                    jsonObject.addProperty("category", category)
                    
                    val image: TmdbImage = gson.fromJson(jsonObject, imageType)
                    images.add(image)
                }
                reader.endArray()
            } else {
                // Skip non-array fields (like "id" which TMDB sometimes includes here)
                reader.skipValue()
            }
        }
        reader.endObject()

        return images
    }
}
