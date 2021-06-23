package com.felipepolo.pokemonappmvvm.di

import android.content.Context
import com.felipepolo.superheroapp.ui.HeroListActivity.di.HeroListComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [AppSubcomponentsModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun MainHeroListComponent(): HeroListComponent.Factory
}

@Module(subcomponents = [HeroListComponent::class])
class AppSubcomponentsModule