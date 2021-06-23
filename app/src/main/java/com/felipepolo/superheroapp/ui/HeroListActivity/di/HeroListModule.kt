package com.felipepolo.superheroapp.ui.HeroListActivity.di

import androidx.lifecycle.ViewModelProvider
import com.felipepolo.superheroapp.data.UserRepository
import com.felipepolo.superheroapp.ui.HeroListActivity.HeroListViewModel
import com.felipepolo.superheroapp.ui.HeroListActivity.MainHeroListActivity
import com.felipepolo.superheroapp.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class HeroListModule {

    @Provides
    fun providesHeroListViewModel(
        activity: MainHeroListActivity,
        repository: UserRepository
    ): HeroListViewModel{
        val factory = ViewModelProviderFactory(repository)
        return ViewModelProvider(activity,factory).get(HeroListViewModel::class.java)
    }
}