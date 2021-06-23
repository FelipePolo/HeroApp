package com.felipepolo.superheroapp.ui.HeroListActivity.di

import com.felipepolo.superheroapp.ui.HeroListActivity.MainHeroListActivity
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(modules = [HeroListModule::class])
interface HeroListComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(@BindsInstance activity: MainHeroListActivity): HeroListComponent
    }

    fun inject(activity: MainHeroListActivity)
}