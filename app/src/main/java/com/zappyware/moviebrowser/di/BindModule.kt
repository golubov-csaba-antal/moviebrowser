package com.zappyware.moviebrowser.di

import com.zappyware.moviebrowser.repository.IMoviesRepository
import com.zappyware.moviebrowser.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface BindModule {

    @Binds
    fun moviesRepository(repository: MoviesRepository): IMoviesRepository
}
