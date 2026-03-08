package com.zappyware.moviebrowser.data.common

enum class Gender {
    Unknown,
    Female,
    Male,
}

fun Int.toGender() = Gender.entries.find { it.ordinal == this } ?: Gender.Unknown
