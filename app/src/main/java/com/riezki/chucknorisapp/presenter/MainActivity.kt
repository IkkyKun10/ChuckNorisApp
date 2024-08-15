package com.riezki.chucknorisapp.presenter

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.LifecycleStartStopEffectScope
import com.riezki.chucknorisapp.presenter.screen.MainScreen
import com.riezki.chucknorisapp.presenter.ui.theme.ChuckNorisAppTheme
import com.riezki.chucknorisapp.presenter.viewmodel.MainViewModel
import com.riezki.chucknorisapp.presenter.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

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
                    LifecycleEventEffect(event = Lifecycle.Event.ON_START) {
                        viewModel.getJokes("animal")
                    }
                    MainScreen(
                        jokesState = jokesState,
                        queryState = viewModel.query,
                        onQueryChange = viewModel::onQueryChange,
                        onGetJokes = {
                            viewModel.getJokes(it)
                        },
                        onItemClicked = {
                            Intent(this, DetailActivity::class.java).also {
                                startActivity(it)
                            }
                        }
                    )
                }
            }
        }
    }
}