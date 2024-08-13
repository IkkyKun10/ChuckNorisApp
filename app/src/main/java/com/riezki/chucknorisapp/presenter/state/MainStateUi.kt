package com.riezki.chucknorisapp.presenter.state

import com.riezki.chucknorisapp.domain.model.JokesDomain

/**
 * @author riezky maisyar
 */

sealed class MainStateUi {
    data object Loading : MainStateUi()
    data class Success(val data: List<JokesDomain>): MainStateUi()
}