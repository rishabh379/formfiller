package com.pvsrishabh.formfiller.presentation.feature.form_filler

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FormFillerViewModel: ViewModel() {
    private val _state = mutableStateOf(FormFillerUiState())
    val state: State<FormFillerUiState> = _state

    fun updateText(newText: String) {
        _state.value = _state.value.copy(text = newText)
    }
}