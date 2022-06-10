package com.example.composeapplication

import android.app.Application

open class PotterApp : Application() {
    open fun getBaseUrl() = "http://hp-api.herokuapp.com/"
}