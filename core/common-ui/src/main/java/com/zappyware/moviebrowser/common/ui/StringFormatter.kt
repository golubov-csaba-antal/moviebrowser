package com.zappyware.moviebrowser.common.ui

import java.util.Locale

class StringFormatter(
    private val pattern: String,
    private val locale: Locale,
) {
    fun format(vararg args: Any?) =
        String.format(locale, pattern, *args)
}
