package com.riezki.chucknorisapp.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
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
                Surface(
                    modifier = Modifier
                        .navigationBarsPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val viewModel by viewModels<MainViewModel> {
                        ViewModelFactory.getInstance(this)
                    }
                    val jokesState by viewModel.jokes.collectAsState()
                    LaunchedEffect(key1 = Unit) {
                        viewModel.getJokes("animal")
                    }
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
}