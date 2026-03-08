package com.zappyware.moviebrowser.page.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.MediaType
import com.zappyware.moviebrowser.data.page.PageWidget
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

    private val _pageWidget = MutableStateFlow<PageWidget?>(null)
    val pageWidget: StateFlow<PageWidget?> get() = _pageWidget

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite

    fun fetchDetailWidget(contentId: String, mediaType: MediaType) {
        viewModelScope.launch(Dispatchers.IO) {
            val widget = moviesRepository.fetchDetailWidget(contentId, mediaType)
            widget?.let {
                _pageWidget.emit(it as PageWidget)
            }
            _isFavorite.emit(moviesRepository.getIsFavoriteMovieById(contentId))
        }
    }

    fun onFavoriteClicked(movieId: String, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesRepository.changeFavorite(movieId, isFavorite)
            _isFavorite.emit(isFavorite)
        }
    }
}
