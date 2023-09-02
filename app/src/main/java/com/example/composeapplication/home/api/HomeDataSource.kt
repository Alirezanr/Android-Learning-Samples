package com.example.composeapplication.home.api

import com.example.composeapplication.home.PostDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val homeDataService: HomeDataService,
) {
    fun getPostsData(): Flow<List<PostDto>> {
        return flow {
            emit(homeDataService.getPostsData())
        }.flowOn(dispatcher)
    }
}