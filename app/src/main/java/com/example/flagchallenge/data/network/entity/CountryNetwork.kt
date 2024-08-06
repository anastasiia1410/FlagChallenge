package com.example.flagchallenge.data.network.entity

import com.example.flagchallenge.ui.entity.Country
import com.google.gson.annotations.SerializedName

data class CountryNetwork(
    @SerializedName("name")
    val countryName : String,
    @SerializedName("flag")
    val flagUrl : String,
    @SerializedName("correctValue")
    val isCorrect : Boolean
)

fun CountryNetwork.toCountry() : Country{
    return Country(
        countryName = this.countryName,
        flagUrl = this.flagUrl,
        isCorrect = this.isCorrect
    )
}