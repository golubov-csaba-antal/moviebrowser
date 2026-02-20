package com.zappyware.moviebrowser.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.MovieWidget
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) : ViewModel() {

    private val _movieWidget = MutableStateFlow<MovieWidget?>(null)
    val movieWidget: StateFlow<MovieWidget?> get() = _movieWidget

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun getMovieById(movieId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            _movieWidget.emit(moviesRepository.getMovieById(movieId))
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
