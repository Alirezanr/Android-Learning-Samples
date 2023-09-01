package com.example.composeapplication.home


import com.example.composeapplication.base.ViewEvent
import com.example.composeapplication.base.ViewSideEffect
import com.example.composeapplication.base.ViewState

class HomeScreenContract {

    sealed class Event : ViewEvent

    data class State(
        val isLoading: Boolean = false,
        val postsListData: List<PostDto>,
        val error: String?,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        object OnError : Effect()
    }
}