package com.example.composeapplication

import retrofit2.Call
import retrofit2.http.GET

interface PotterApi {

    @GET("api/characters")
    fun getCharacters(): Call<List<CharacterModel>>
}