package com.example.composeapplication.data

import com.apollographql.apollo3.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.composeapplication.domain.repository.CountryClient
import com.example.composeapplication.domain.model.DetailedCountry
import com.example.composeapplication.domain.model.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient {
    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(country_code = code))
            .execute()
            .data
            ?.country
            ?.toDetailedCountry()
    }
}