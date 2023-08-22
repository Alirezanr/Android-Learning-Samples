package com.example.composeapplication.domain.model

data class DetailedCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val continent: String,
    val languages: List<String>,
)
