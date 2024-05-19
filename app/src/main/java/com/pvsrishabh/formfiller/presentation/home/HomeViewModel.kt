package com.pvsrishabh.formfiller.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeViewModel: ViewModel() {

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    init {
        _state.value = _state.value.copy(user = Firebase.auth.currentUser)
    }

    fun updateText(newText: String) {
        _state.value = _state.value.copy(text = newText)
    }

}