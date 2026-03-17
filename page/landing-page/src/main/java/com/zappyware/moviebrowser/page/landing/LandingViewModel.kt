package com.zappyware.moviebrowser.page.landing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zappyware.moviebrowser.common.ui.FavoriteProvider
import com.zappyware.moviebrowser.data.tray.TrayWidget
import com.zappyware.moviebrowser.repository.IMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val moviesRepository: IMoviesRepository,
) : ViewModel(), FavoriteProvider {

    val trays = MutableSharedFlow<List<TrayWidget>>(
        replay = 1,
        extraBufferCapacity = 0,
    )

    fun fetchLandingTrays() {
        viewModelScope.launch {
            trays.emit(
                moviesRepository.fetchLandingTrays()
            )
        }
    }

    override suspend fun isFavorite(contentId: String): Boolean =
        moviesRepository.getIsFavoriteMovieById(contentId)
}
