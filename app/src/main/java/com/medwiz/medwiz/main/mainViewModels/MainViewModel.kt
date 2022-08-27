package com.medwiz.medwiz.main.mainViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val _isLoading= MutableStateFlow(false)
    val isLoading=_isLoading.asStateFlow()
    init {
        viewModelScope.launch {
            delay(10000)
            _isLoading.value=false
        }
    }
}