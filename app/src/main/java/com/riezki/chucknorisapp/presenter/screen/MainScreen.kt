package com.riezki.chucknorisapp.presenter.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.riezki.chucknorisapp.domain.model.JokesDomain
import com.riezki.chucknorisapp.presenter.state.MainStateUi

/**
 * @author riezkymaisyar
 */


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MainScreen(
    jokesState: MainStateUi,
    queryState: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onGetJokes: (String) -> Unit,
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = queryState,
            onValueChange = {
                onQueryChange(it)
            },
            label = { Text("Search") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onGetJokes(queryState)
                    keyboardController?.hide()
                },
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (jokesState is MainStateUi.Loading) {
                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            if (jokesState is MainStateUi.Success) {
                items(
                    items = jokesState.data,
                    key = { it.id.toString() }
                ) {
                    HomeItemScreens(jokesItem = it) {
                        Toast.makeText(
                            context,
                            "this item with id ${it.id}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                item {
                    if (jokesState.data.isEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = "No Jokes Found", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPrev() {
    MainScreen(
        jokesState = MainStateUi.Success(
            (1..10).map {
                JokesDomain(
                    categories = null,
                    createdAt = "22-12-2023",
                    iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
                    id = it.toString(),
                    updatedAt = "22-12-2023",
                    value = "yeyey yeyey yeyey",
                    url = "https://api.chucknorris.io/jokes/123"
                )
            },
        ),
        queryState = "",
        onQueryChange = {},
        onGetJokes = {}
    )
}