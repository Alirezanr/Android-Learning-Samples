package com.example.composeapplication.home.api


import com.example.composeapplication.home.PostDto
import retrofit2.http.GET

interface HomeDataService {

    @GET("/public/v2/posts")
    suspend fun getPostsData(): List<PostDto>

}