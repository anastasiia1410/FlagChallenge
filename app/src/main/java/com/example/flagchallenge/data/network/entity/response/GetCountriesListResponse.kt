package com.example.flagchallenge.data.network.entity.response

import com.example.flagchallenge.data.network.entity.CountryNetwork
import com.google.gson.annotations.SerializedName

data class GetCountriesListResponse(
    @SerializedName("time")
    val time : Long,
    @SerializedName("data")
    val data : List<CountryNetwork>
)