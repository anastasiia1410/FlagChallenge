package com.example.flagchallenge.data.network

import com.example.flagchallenge.data.network.entity.response.GetCountriesListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("countrie/random")
    suspend fun getCountries(@Query ("limit") limit : Int = 2) : GetCountriesListResponse
}