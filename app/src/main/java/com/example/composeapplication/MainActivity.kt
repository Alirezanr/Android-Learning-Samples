package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeapplication.presentation.CountriesScreen
import com.example.composeapplication.presentation.CountriesViewModel
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsState()

                CountriesScreen(
                    state = state,
                    onSelectCountry = {
                        viewModel.selectCountry(it)
                    },
                    onDismissDialog = {
                        viewModel.dismissCountryDialog()
                    }
                )
            }
        }
    }
}

/**
 * Notes:
 *
 * Apollo graphql only works with the kotlin dsl.
 *
 * To download schema:(package name specified in build.gradle-> apollo)
 * ./gradlew :app:downloadApolloSchema --endpoint='https://countries.trevorblades.com/graphql' --schema=app/src/main/graphql/com/example/schema.graphqls
 *
 *
 *
 * */