package com.zappyware.moviebrowser.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.Movie
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) : ViewModel() {

    fun getMovieById(movieId: Long, callback: (Movie?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = moviesRepository.getMovieById(movieId)
            withContext(Dispatchers.Main) {
                callback.invoke(movie)
            }
        }
    }

    fun onFavoriteClicked(movieId: Long, isFavorite: Boolean) {
        viewModelScope.launch {
            moviesRepository.changeFavorite(movieId, isFavorite)
        }
    }
}
