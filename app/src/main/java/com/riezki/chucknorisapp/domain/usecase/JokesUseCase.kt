package com.riezki.chucknorisapp.domain.usecase

import com.riezki.chucknorisapp.domain.repository.JokesRepository

/**
 * @author riezkymaisyar
 */

class JokesUseCase(
   private val repository: JokesRepository
) {

    fun getJokes(query: String) = repository.getJokes(query)
}