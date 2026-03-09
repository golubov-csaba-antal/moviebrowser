package com.zappyware.moviebrowser.network.tmdb

import com.zappyware.moviebrowser.network.tmdb.data.entities.TmdbMovie
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbInterval
import com.zappyware.moviebrowser.network.tmdb.data.enums.TmdbMediaType
import com.zappyware.moviebrowser.network.tmdb.data.page.TmdbDetailPage
import com.zappyware.moviebrowser.network.tmdb.response.GenreListResponse
import com.zappyware.moviebrowser.network.tmdb.response.ImageListResponse
import com.zappyware.moviebrowser.network.tmdb.response.MovieListResponse
import com.zappyware.moviebrowser.network.tmdb.response.PeopleListResponse
import com.zappyware.moviebrowser.network.tmdb.response.VideoListResponse
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

    @GET("trending/person/{interval}")
    suspend fun getTrendingPeople(
        @Header("Authorization") auth: String,
        @Path("interval",) interval: TmdbInterval,
        @Query("language") language: String
    ): PeopleListResponse

    @GET("{mediaType}/{interval}")
    suspend fun getVideoList(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Path("interval",) interval: TmdbInterval,
        @Query("language") language: String
    ): VideoListResponse

    @GET("trending/{mediaType}/{interval}")
    suspend fun getImageList(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Path("interval",) interval: TmdbInterval,
        @Query("language") language: String
    ): ImageListResponse

    @GET("{mediaType}/now_playing")
    suspend fun getNowPlaying(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Query("language") language: String
    ): MovieListResponse

    @GET("{mediaType}/top_rated")
    suspend fun getTopRated(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Query("language") language: String
    ): MovieListResponse

    @GET("{mediaType}/upcoming")
    suspend fun getUpcoming(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Query("language") language: String
    ): MovieListResponse

    @GET("{mediaType}/popular")
    suspend fun getPopular(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Query("language") language: String
    ): MovieListResponse

    @GET("{mediaType}/{contentId}")
    suspend fun getDetails(
        @Header("Authorization") auth: String,
        @Path("mediaType") mediaType: TmdbMediaType,
        @Path("contentId") contentId: String,
        @Query("language") language: String,
        @Query("append_to_response") append: String,
    ): TmdbDetailPage
}
