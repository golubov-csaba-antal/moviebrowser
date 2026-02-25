package com.zappyware.moviebrowser.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zappyware.moviebrowser.network.INetworkService
import com.zappyware.moviebrowser.network.tmdb.TmdbService
import com.zappyware.moviebrowser.util.body
import com.zappyware.moviebrowser.util.isDebuggable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(ViewModelComponent::class)
class ProvideModule {

    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .apply {
                if (context.isDebuggable()) {
                    addInterceptor(HttpLoggingInterceptor().body())
                }
            }
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideNetworkService(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): INetworkService =
        TmdbService(okHttpClient, gson)
}
