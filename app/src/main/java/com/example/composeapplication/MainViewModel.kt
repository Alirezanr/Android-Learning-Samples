package com.example.composeapplication

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _list = getWellnessTasks().toMutableStateList()
    val list: List<WellnessTask>
        get() = _list


    fun removeTask(task: WellnessTask) {
        _list.remove(task)
    }


    private fun getWellnessTasks(): List<WellnessTask> =
        List(15) {
            WellnessTask(it, "Task number $it")
        }

    fun checkChanged(task: WellnessTask) {
        _list[_list.indexOf(task)] = task.copy(isChecked = !task.isChecked)
    }
}