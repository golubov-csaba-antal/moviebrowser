package com.zappyware.moviebrowser.page.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.data.TrayWidget
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) : ViewModel() {

    val trays = MutableSharedFlow<List<TrayWidget>>(
        replay = 1,
        extraBufferCapacity = 0,
    )

    fun fetchTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val tray = moviesRepository.fetchTrendingMoviesTray()
            trays.emit(
                listOf(
                    tray, tray, tray, tray, tray, tray, tray, tray, tray, tray, tray, tray,
                )
            )
        }
    }
}
