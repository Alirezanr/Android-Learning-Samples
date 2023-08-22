package com.example.composeapplication.data


import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.composeapplication.domain.model.DetailedCountry
import com.example.composeapplication.domain.model.SimpleCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry =
    DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        continent = continent.name,
        languages = languages.mapNotNull { it.name },
    )

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry =
    SimpleCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
    )