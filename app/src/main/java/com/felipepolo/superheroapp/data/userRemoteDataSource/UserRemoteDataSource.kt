package com.felipepolo.superheroapp.data.userRemoteDataSource

import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.HeroApiRetrofitService
import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure.ApiHeroService
import com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure.Hero
import com.felipepolo.superheroapp.utils.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val networkManager: NetworkManager) {
    private val heroApiRetrofit = HeroApiRetrofitService.getHeroApiRetrofit()

    suspend fun getHero(id:String): Hero {
        return withContext(Dispatchers.IO) {
            if(networkManager.userIsConnect()){
                val heroResponse = heroApiRetrofit.create(ApiHeroService::class.java).getHero(id)
                return@withContext heroResponse.body()!!
            }

            throw Exception("Check Your Internet...")
        }
    }

    suspend fun getHerosByName(name:String): ArrayList<Hero> {
        return withContext(Dispatchers.IO) {
            if(networkManager.userIsConnect()){
                val heroResponse = heroApiRetrofit.create(ApiHeroService::class.java).getHerosByName(name)
                val responseBody = heroResponse.body()
                if (heroResponse.isSuccessful && responseBody?.results != null){
                    return@withContext responseBody.results
                }
                return@withContext ArrayList()
            }
            throw Exception("Check Your Internet...")
        }
    }
}