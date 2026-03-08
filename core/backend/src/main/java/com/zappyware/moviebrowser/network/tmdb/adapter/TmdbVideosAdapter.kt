package com.zappyware.moviebrowser.network.tmdb.adapter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbVideo

class TmdbVideosAdapter : TypeAdapter<List<TmdbVideo>>() {

    private val gson = Gson()
    private val videoType = object : TypeToken<TmdbVideo>() {}.type

    override fun write(out: JsonWriter, value: List<TmdbVideo>?) {
        if (value == null) {
            out.nullValue()
            return
        }
        out.beginObject()
        out.name("results")
        out.beginArray()
        for (video in value) {
            gson.toJson(video, videoType, out)
        }
        out.endArray()
        out.endObject()
    }

    override fun read(reader: JsonReader): List<TmdbVideo> {
        val videos = mutableListOf<TmdbVideo>()

        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return emptyList()
        }

        reader.beginObject()
        while (reader.hasNext()) {
            val name = reader.nextName()
            if (name == "results" && reader.peek() != JsonToken.NULL) {
                reader.beginArray()
                while (reader.hasNext()) {
                    val video: TmdbVideo = gson.fromJson(reader, videoType)
                    videos.add(video)
                }
                reader.endArray()
            } else {
                reader.skipValue()
            }
        }
        reader.endObject()

        return videos
    }
}
