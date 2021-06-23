package com.felipepolo.superheroapp.data.userRemoteDataSource.HeroApiRetrofitServices.dataStructure

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchHeros(
    val response: String,
    val results: ArrayList<Hero>
)


data class Hero(
    var response: String,
    var error: String,

    var id: String,
    var name: String,
    var powerstats: PowerStats,
    var biography: Biography,
    var appearance: Appearance,
    var work: Work,
    var connections: Connections,
    var image: HeroImage
): Serializable

data class PowerStats(
    var intelligence: String,
    var strength: String,
    var speed: String,
    var durability: String,
    var power: String,
    var combat: String
): Serializable

data class Biography(
    @SerializedName("full-name")
    var fullName: String,
    @SerializedName("alter-egos")
    var alterEgos: String,
    var aliases: ArrayList<String>,
    @SerializedName("place-of-birth")
    var birth: String,
    @SerializedName("first-appearance")
    var Firstapperance: String,
    var publisher: String,
    var alignment: String
): Serializable

data class Appearance(
    var gender: String,
    var race: String,
    var height: ArrayList<String>,
    var weight: ArrayList<String>,
    @SerializedName("eye-color")
    var eyeColor: String,
    @SerializedName("hair-color")
    var hairColor: String
): Serializable

data class Work(
    var occupation: String,
    var base: String
): Serializable

data class Connections(
    @SerializedName("group-affiliation")
    var affiliation: String,
    var relatives: String
): Serializable

data class HeroImage(
    var url: String
): Serializable