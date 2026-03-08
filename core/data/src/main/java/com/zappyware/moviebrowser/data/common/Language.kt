package com.zappyware.moviebrowser.data.common

import java.util.Locale

data class Language(
    val code: String,
    val country: String? = null,
    val name: String? = null
)

fun Language.getUserReadableLanguage(): String {
    return name ?: run {
        val locale = country?.let { Locale(code, country) } ?: Locale(code)
        locale.getDisplayLanguage(locale)
    }
}
