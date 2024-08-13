package com.riezki.chucknorisapp.domain.repository

import com.riezki.chucknorisapp.domain.model.JokesDomain
import kotlinx.coroutines.flow.Flow

/**
 * @author riezkymaisyar
 */

interface JokesRepository {
    fun getJokes(query: String): Flow<List<JokesDomain>>
}