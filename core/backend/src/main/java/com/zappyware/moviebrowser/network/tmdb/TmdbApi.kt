package com.zappyware.moviebrowser.network.tmdb

import com.zappyware.moviebrowser.network.tmdb.response.GenreListResponse
import com.zappyware.moviebrowser.network.tmdb.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("genre/{mediaType}/list")
    suspend fun getGenres(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: String,
        @Query("language") language: String
    ): GenreListResponse

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Header("Authorization") auth: String,
        @Query("language") language: String
    ): MovieListResponse
}
