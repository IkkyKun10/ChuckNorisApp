package com.riezki.chucknorisapp.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.riezki.chucknorisapp.presenter.screen.MainScreen
import com.riezki.chucknorisapp.presenter.ui.theme.ChuckNorisAppTheme
import com.riezki.chucknorisapp.presenter.viewmodel.MainViewModel
import com.riezki.chucknorisapp.presenter.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

//    private val viewModel by viewModels<MainViewModel> {
//        ViewModelFactory.getInstance(this)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChuckNorisAppTheme {
                val viewModel by viewModels<MainViewModel> {
                    ViewModelFactory.getInstance(this)
                }
                val jokesState by viewModel.jokes.collectAsState()
                MainScreen(
                    jokesState = jokesState,
                    queryState = viewModel.query,
                    onQueryChange = {
                        viewModel.onQueryChange(it)
                    },
                    onGetJokes = {
                        viewModel.getJokes(it)
                    }
                )
            }
        }
    }
}