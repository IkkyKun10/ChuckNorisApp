package com.riezki.chucknorisapp.presenter.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.riezki.chucknorisapp.core.di.Injection
import com.riezki.chucknorisapp.domain.usecase.JokesUseCase

/**
 * @author riezkymaisyar
 */

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val jokesUseCase: JokesUseCase ) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(jokesUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideUseCase(context)).also {
                    instance = it
                }
            }
        }
    }
}