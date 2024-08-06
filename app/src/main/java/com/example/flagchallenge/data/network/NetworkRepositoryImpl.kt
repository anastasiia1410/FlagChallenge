package com.example.flagchallenge.data.network

import com.example.flagchallenge.data.network.entity.toCountry
import com.example.flagchallenge.ui.entity.Country

class NetworkRepositoryImpl(private val api : Api) : NetworkRepository {

    override suspend fun getCountries(): List<Country> {
        return api.getCountries().data.map { it.toCountry() }
    }

}