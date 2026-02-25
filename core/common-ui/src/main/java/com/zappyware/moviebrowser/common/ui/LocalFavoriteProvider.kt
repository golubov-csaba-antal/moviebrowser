package com.zappyware.moviebrowser.common.ui

import androidx.compose.runtime.compositionLocalOf

interface FavoriteProvider {
    suspend fun isFavorite(contentId: Long): Boolean
}

val LocalFavoriteProvider = compositionLocalOf<FavoriteProvider> { error("No favorite provider") }
