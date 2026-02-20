package com.zappyware.moviebrowser.page.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.MovieWidget
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
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
        viewModelScope.launch {
            moviesRepository.fetchMovies()
                .flowOn(Dispatchers.Main)
                .catch {
                    // error handling
                }
                .collect {
                    movies.emit(it.data.orEmpty())
                }
        }
    }
}
