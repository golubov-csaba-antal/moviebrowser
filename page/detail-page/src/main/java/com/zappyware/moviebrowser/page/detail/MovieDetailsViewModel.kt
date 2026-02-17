package com.zappyware.moviebrowser.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.repository.IMoviesRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    val moviesRepository: IMoviesRepository,
) : ViewModel() {

    val movie = moviesRepository.movies.map { it.firstOrNull { it.id == moviesRepository.detailsId.value } }

    fun onFavoriteClicked(isFavorite: Boolean) {
        viewModelScope.launch {
            moviesRepository.changeFavorite(isFavorite)
        }
    }
}
