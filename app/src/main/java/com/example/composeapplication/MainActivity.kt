package com.example.composeapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityRetainedScope
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * NOTE: Remember to implement AndroidScopeComponent and
 * override scope variable.
 * */
class MainActivity : ComponentActivity(),
    AndroidScopeComponent {
    //How to inject ViewModel in XML projects:
    //private val viewModel by viewModel<MainViewModel>()

    //Immediate injection that will create an instance right away
    //private val api  = get<MyApi>()

    //Lazy injection:
    //private val api by inject<MyApi>()

    /**
     * Scope injected variables to lifecycle of activity.
     */
    override val scope: Scope by activityScope()

    /**
     * Scope injected variables to lifecycle of activity
     * but like viewmodels lifecycle, variables won't lose their state while
     * configuration changes.
     * NOTE: only use it in XML projects with AppComponentActivity.
     */
    //override val scope: Scope by activityRetainedScope()

    //injects provided string in activityScope module:
    private val hello by inject<String>(named("HELLO"))
    private val bye by inject<String>(named("BYE"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("!!!", "onCreate: $hello")
        Log.i("!!!", "onCreate: $bye")
        setContent {
            ComposeApplicationTheme {
                //How to inject ViewModel in compose projects
                val viewModel = getViewModel<MainViewModel>()
                viewModel.printSomething()
            }
        }
    }
}
