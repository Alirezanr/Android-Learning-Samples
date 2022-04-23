package com.example.composeapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    /**
     * Cold flows: emits nothing until somewhere we collect its data
     * */
    val countDownFlow = flow<Int> {
        val startValue = 5
        var countDownValue = startValue
        emit(countDownValue)
        while (countDownValue > 0) {
            delay(1000)
            countDownValue--
            emit(countDownValue)
        }
    }

    /**
     * Hot flows: emits value if there are no collectors.
     * StateFlow will re-emit values when new collector subscribes to it (like when screen rotation happens).
     * */
    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()


    /**
     * Sends one-time events like channels(doesn't re-emit values).
     * channels can have only one subscriber(collector) but SharedFlows can have multiple subscribers (collector).
     * We can give initial integer value as cache size
     *   so whenever a new collector subscribes to shared flow, gets last 5 cached emits.
     */
    private val _sharedFlow = MutableSharedFlow<Int>(replay = 5)
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        handleSharedFlow()
    }

    private fun handleSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit(1)
            _sharedFlow.emit(2)
            _sharedFlow.emit(3)
            _sharedFlow.emit(4)
            _sharedFlow.emit(5)
            _sharedFlow.emit(6)
        }
        viewModelScope.launch {
            sharedFlow.collect { number ->
                Log.i("!!!", "Value is $number")
            }
        }
    }

    //State flow :
    fun increment() {
        _stateFlow.value += 1
    }

    //-------------------
    //Flow operators:
    private fun flowSimpleOperators() {
        viewModelScope.launch {
            countDownFlow
                .map { time ->
                    //transform values of flow.
                    //return transformed value.
                    time + (time * 2)
                }
                .filter { time ->
                    //filters values of flow by a condition.
                    time % 2 == 0
                }
                .onEach { time ->
                    //its just like collect{} but this returns the value of flow
                    // and can be used outside of viewModelScope.
                    //Log.i("!!!", "Value is $time")

                    //use this like below:
                    /*countDownFlow.onEach { time ->
                        Log.i("!!!", "Value is $time")
                    }.launchIn(viewModelScope)*/
                }
                .collect { time ->
                    // Log.i("!!!", "Value is $time")
                }
        }
    }

    private fun flowTerminalOperators() {
        viewModelScope.launch {
            val countedFlows = countDownFlow.count { time ->
                //returns number of emissions that suits this condition.
                time % 2 == 0
            }
            val reduceResult = countDownFlow.reduce { accumulator, value ->
                //in each emission takes value and adds it to accumulator and returns final value of accumulator.
                //Log.i("!!!", "accumulator is $accumulator and value is $value")
                accumulator + value
            }
            val foldResult = countDownFlow.fold(100) { accumulator, value ->
                //just like reduce but we should give an initial value to accumulator as function parameter.
                Log.i("!!!", "accumulator is $accumulator and value is $value")
                accumulator + value
            }
            Log.i("!!!", "Value is $foldResult")
        }
    }

    private fun flattenFlowOperators() {
        val ids = (1..5).asFlow()
        viewModelScope.launch {
            ids.flatMapConcat { id ->
                //combines result of a flow with inner flow.
                flow {
                    delay(500)
                    emit(id + 1)
                }
            }.collect { id ->
                // Log.i("!!!", "Value is $id")
            }
            //---------------------------
            ids.flatMapMerge { id ->
                //combines result of a flow with inner flow in same time(doesn't wait for inner flow to finish).
                flow {
                    delay(500)
                    emit(id + 1)
                }
            }.collect { id ->
                //Log.i("!!!", "Value is $id")
            }
            //---------------------------
            ids.flatMapLatest { id ->
                //combines result of a flow with inner flow(cancels inner flow if it takes too long).
                flow {
                    delay(500)
                    emit(id + 1)
                }
            }.collect { id ->
                Log.i("!!!", "Value is $id")
            }
        }
    }

    private fun bufferFlowOperators() {
        val mealFlow = flow {
            delay(250)
            emit("Appetizer")

            delay(1000)
            emit("Main dish")

            delay(250)
            emit("Dessert")
        }

        viewModelScope.launch {
            mealFlow
                .onEach { meal ->
                    Log.i("!!!", "$meal is delivered.")
                }
                .conflate()//drops or skips all the emissions that didn't or couldn't collect.

                //or .buffer() //buffers emissions
                .collect { meal ->
                    Log.i("!!!", "Now eating $meal")
                    delay(1500)
                    Log.i("!!!", "Finished eating $meal")
                }
        }
    }
}