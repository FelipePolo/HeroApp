package com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object HeroApiRetrofitService {
    private val MY_ACCESS_TOKEN = "2632107390426021"
    private val BASE_SUPERHEROAPI_URL = "https://superheroapi.com/api/${MY_ACCESS_TOKEN}/"

    @Singleton
    fun getHeroApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_SUPERHEROAPI_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}