package com.zappyware.moviebrowser.network.tmdb

import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbMovie
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbInterval
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbMediaType
import com.zappyware.moviebrowser.network.tmdb.data.page.TmdbDetailPage
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
        @Path("mediaType") mediaType: TmdbMediaType,
        @Query("language") language: String
    ): GenreListResponse

    @GET("trending/{mediaType}/{interval}")
    suspend fun getTrending(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Path("interval",) interval: TmdbInterval,
        @Query("language") language: String
    ): MovieListResponse

    @GET("{mediaType}/latest")
    suspend fun getLatest(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Query("language") language: String
    ): TmdbMovie

    @GET("{mediaType}/{contentId}")
    suspend fun getDetails(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Path("contentId") contentId: String,
        @Query("language") language: String,
        @Query("append_to_response") append: List<String>,
    ): TmdbDetailPage
}
