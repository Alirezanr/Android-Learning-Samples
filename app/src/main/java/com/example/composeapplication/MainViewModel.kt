package com.example.composeapplication

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun printSomething(){
        Log.i("!!!", "printSomething: ")
    }

}