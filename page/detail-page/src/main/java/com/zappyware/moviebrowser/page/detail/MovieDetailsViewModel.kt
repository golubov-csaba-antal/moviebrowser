package com.zappyware.moviebrowser.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.Movie
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> get() = _movie

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun getMovieById(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _movie.emit(moviesRepository.getMovieById(movieId))
            _isFavorite.emit(moviesRepository.getIsFavoriteMovieById(movieId))
        }
    }

    fun onFavoriteClicked(movieId: Long, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.changeFavorite(movieId, isFavorite)
            _isFavorite.emit(isFavorite)
        }
    }
}
