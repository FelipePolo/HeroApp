package com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiHeroService {
    @GET("{id}")
    suspend fun getHero(@Path("id") id:String): Response<Hero>

    @GET("search/{heroName}")
    suspend fun getHerosByName(@Path("heroName") heroName: String): Response<SearchHeros>
}