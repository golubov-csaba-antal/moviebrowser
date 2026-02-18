package com.zappyware.moviebrowser.repository

import com.zappyware.moviebrowser.common.ui.ViewState
import com.zappyware.moviebrowser.data.Movie
import com.zappyware.moviebrowser.database.dao.MoviesDao
import com.zappyware.moviebrowser.database.entity.toMBMovie
import com.zappyware.moviebrowser.database.entity.toMovie
import com.zappyware.moviebrowser.network.INetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val service: INetworkService,
    private val moviesDao: MoviesDao,
): IMoviesRepository {
    private val moviesXIsFavorite: MutableMap<Long,Boolean> = mutableMapOf()

    override val movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    override suspend fun fetchMovies(): Flow<ViewState<List<Movie>>> =
        flow {
            emit(ViewState.loading<List<Movie>>())
            val movies = service.getTrendingMovies()
                .onEach {
                    it.isFavorite = moviesXIsFavorite[it.id] ?: false
                }.also {
                    moviesDao.clearMovies()
                    moviesDao.saveMovies(it.map { it.toMBMovie() })
                }
            emit(ViewState.success(movies))
        }.flowOn(Dispatchers.IO)

    override suspend fun changeFavorite(id: Long, isFavorite: Boolean) {
        moviesXIsFavorite[id] = isFavorite
        val index = movies.value.indexOfFirst { it.id == id }
        movies.value.toMutableList().also {
            it[index] = it[index].copy(isFavorite = isFavorite)
        }.also {
            moviesDao.saveMovie(it[index].toMBMovie())
            movies.emit(it)
        }
    }

    override suspend fun getMovieById(id: Long): Movie? {
        return moviesDao.getMovieByContentId(id)?.toMovie()
    }
}
