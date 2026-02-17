package com.zappyware.moviebrowser.page.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.Movie
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) : ViewModel() {

    val movies = moviesRepository.movies

    init {
        viewModelScope.launch {
            moviesRepository.fetchMovies()
                .flowOn(Dispatchers.Main)
                .catch {
                    // error handling
                }
                .collect {
                    moviesRepository.movies.emit(it.data.orEmpty())
                }
        }
    }

    fun storeMovieForNavigation(movie: Movie) {
        moviesRepository.detailsId.value = movie.id
    }
}
