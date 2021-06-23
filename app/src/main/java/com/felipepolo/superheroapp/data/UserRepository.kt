package com.felipepolo.superheroapp.data

import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure.Hero
import com.felipepolo.superheroapp.data.userRemoteDataSource.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource) {

    suspend fun getHeroById(id: String): Hero {
        return userRemoteDataSource.getHero(id)
    }

    suspend fun getHerosByName(name:String): ArrayList<Hero>{
        return userRemoteDataSource.getHerosByName(name)
    }
}