package com.example.composeapplication.home.api

import com.example.composeapplication.home.PostDto
import kotlinx.coroutines.flow.Flow

class HomeRepository(
    private val dataSource: HomeDataSource
) {
    fun getPostsData(): Flow<List<PostDto>> {
        return dataSource.getPostsData()
    }
}
