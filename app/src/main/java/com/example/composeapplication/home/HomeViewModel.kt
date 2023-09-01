package com.example.composeapplication.home

import androidx.lifecycle.viewModelScope
import com.example.composeapplication.base.BaseViewModel
import com.example.composeapplication.home.api.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val repository: HomeRepository
) : BaseViewModel<
        HomeScreenContract.Event,
        HomeScreenContract.State,
        HomeScreenContract.Effect>() {

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            setState {
                copy(
                    error = throwable.message,
                    isLoading = false
                )
            }
        }
        setEffect { HomeScreenContract.Effect.OnError }
    }

    init {
        fetchPostsData()
    }

    fun fetchPostsData() {
        viewModelScope.launch(dispatcher + exceptionHandler) {
            repository.getPostsData()
                .collect { postsData ->
                    updatePostsDataToView(postsData)
                }
        }
    }

    private fun updatePostsDataToView(postsData: List<PostDto>) {
        viewModelScope.launch {
            setState {
                copy(
                    postsListData = postsData,
                    isLoading = false
                )
            }
        }
    }

    override fun setInitialState(): HomeScreenContract.State =
        HomeScreenContract.State(
            isLoading = true,
            postsListData = emptyList(),
            error = null
        )

}
