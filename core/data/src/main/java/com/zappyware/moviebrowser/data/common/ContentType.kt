package com.zappyware.moviebrowser.data.common

enum class ContentType {
    Unknown,
    Scripted,
}

fun findContentType(type: String?): ContentType {
    return if (null == type) ContentType.Unknown
    else ContentType.entries.find { it.name == type } ?: ContentType.Unknown
}
