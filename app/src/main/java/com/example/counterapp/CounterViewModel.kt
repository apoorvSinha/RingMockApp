package com.example.counterapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    private val _count = mutableStateOf(0)
    // Expose count as immutable state
    val count: State<Int> = _count

    fun incrementCounter() {
        _count.value++
    }

    fun decrementCounter() {
        _count.value--
    }
}
