package com.riezki.chucknorisapp.presenter.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.riezki.chucknorisapp.domain.model.JokesDomain
import com.riezki.chucknorisapp.domain.usecase.JokesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @author riezkymaisyar
 */

class MainViewModel(
    private val jokesUseCase: JokesUseCase
) : ViewModel() {

    var query by mutableStateOf("")
        private set

    private val _jokes = MutableStateFlow<List<JokesDomain>>(emptyList())
    val jokes = _jokes.asStateFlow()

    fun getJokes(query: String) = viewModelScope.launch {
        jokesUseCase.getJokes(query).collect {
            _jokes.value = it
        }
    }

    fun onQueryChange(query: String) {
        this.query = query
    }

}