package com.riezki.chucknorisapp.core.di

import android.content.Context
import com.riezki.chucknorisapp.core.repository.JokesRepositoryImpl
import com.riezki.chucknorisapp.core.service.ApiConfig
import com.riezki.chucknorisapp.domain.repository.JokesRepository
import com.riezki.chucknorisapp.domain.usecase.JokesUseCase

/**
 * @author riezkymaisyar
 */

object Injection {
    fun provideUseCase(context: Context): JokesUseCase {
        val apiService = ApiConfig.getApiService()
        val repository: JokesRepository = JokesRepositoryImpl(apiService)
        return JokesUseCase(repository)
    }
}