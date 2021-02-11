package com.spudpickles.itunessearch.di

import android.app.Application
import com.spudpickles.itunessearch.SearchApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        BindingModule::class,
        DependencyModule::class,
        NetworkModule::class
    ]
)

interface AppComponent : AndroidInjector<SearchApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
