package com.example.composeapplication

import retrofit2.http.GET

interface MyApi {

    @GET("url/endpoint")
    fun callApi()
}