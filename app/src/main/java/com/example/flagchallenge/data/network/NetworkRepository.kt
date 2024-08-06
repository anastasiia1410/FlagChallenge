package com.example.flagchallenge.data.network

import com.example.flagchallenge.ui.entity.Country

interface NetworkRepository {

    suspend fun getCountries() : List<Country>
}