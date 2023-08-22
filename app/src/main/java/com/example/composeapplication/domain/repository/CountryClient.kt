package com.example.composeapplication.domain.repository

import com.example.composeapplication.domain.model.DetailedCountry
import com.example.composeapplication.domain.model.SimpleCountry

interface CountryClient {

    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?

}