package com.example.composeapplication.domain.use_case

import com.example.composeapplication.domain.model.DetailedCountry
import com.example.composeapplication.domain.repository.CountryClient

class GetCountryDetailUseCase(
    private val countryClient: CountryClient
) {

    suspend operator fun invoke(code: String): DetailedCountry? =
        countryClient.getCountry(code)

}