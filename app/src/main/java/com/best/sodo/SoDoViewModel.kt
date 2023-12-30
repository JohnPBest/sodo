package com.best.sodo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class SoDoViewModel: ViewModel() {

    val sodos = MutableStateFlow(mutableStateListOf(
        SoDo("Breakfast", "Eat breakfast."),
        SoDo("Lunch", "Eat lunch.")
    ))

    fun add(sodo: SoDo) {
        sodos.value.add(sodo)
    }
}