package com.example.composeapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

//kotlin delegation sample
fun main() {
    SomeClass(5, 6).doCalculate()
}

class SomeClass(val firstNumber: Int, val secondNumber: Int) :
    Calculator by CalculatorImpl(firstNumber, secondNumber) {
    fun doCalculate() {
        println(sumOf())
    }
}

interface Calculator {
    fun sumOf(): Int
}

class CalculatorImpl(private val firstNumber: Int, private val secondNumber: Int) : Calculator {

    override fun sumOf(): Int {
        return firstNumber + secondNumber
    }
}