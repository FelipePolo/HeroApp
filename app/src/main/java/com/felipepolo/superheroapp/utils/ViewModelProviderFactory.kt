package com.felipepolo.superheroapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.felipepolo.superheroapp.data.UserRepository
import com.felipepolo.superheroapp.ui.HeroListActivity.HeroListViewModel
import java.lang.IllegalArgumentException

class ViewModelProviderFactory(private val repository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HeroListViewModel::class.java)){
            return HeroListViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class ${modelClass::class.java}")

    }

}
