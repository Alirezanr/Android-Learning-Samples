package com.example.composeapplication.domain.use_case

import com.example.composeapplication.domain.model.SimpleCountry
import com.example.composeapplication.domain.repository.CountryClient

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {
    suspend operator fun invoke(): List<SimpleCountry> =
        countryClient
            .getCountries()
            .sortedBy {
                it.name
            }

}