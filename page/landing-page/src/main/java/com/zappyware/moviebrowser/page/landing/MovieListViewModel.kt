package com.zappyware.moviebrowser.page.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.MovieWidget
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) : ViewModel() {

    val movies = MutableSharedFlow<List<MovieWidget>>(
        replay = 1,
        extraBufferCapacity = 0,
    )

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            movies.emit(
                moviesRepository.fetchMovies()
            )
        }
    }
}
