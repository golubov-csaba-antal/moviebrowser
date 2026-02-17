package com.zappyware.moviebrowser.di

import com.zappyware.moviebrowser.repository.IMoviesRepository
import com.zappyware.moviebrowser.repository.MoviesRepository
import dagger.Binds
import dagger.Module

@Module
interface BindModule {

    @Binds
    fun moviesRepository(repository: MoviesRepository): IMoviesRepository
}
