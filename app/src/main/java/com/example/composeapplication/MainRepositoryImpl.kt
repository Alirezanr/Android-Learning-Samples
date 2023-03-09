package com.example.composeapplication

class MainRepositoryImpl(
    private val api: MyApi
) : MainRepository {

    override fun doNetworkCall() {
        api.callApi()
    }
}