package com.zappyware.moviebrowser.di

import android.content.Context
import com.zappyware.moviebrowser.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        BindModule::class,
        ProvideModule::class
    ]
)
interface MainActivityComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MainActivityComponent
    }

    fun inject(activity: MainActivity)
}
