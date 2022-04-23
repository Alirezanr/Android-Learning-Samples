package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                val viewModel = viewModel<MainViewModel>()
                //how to collect simple flow:
                val timer = viewModel.countDownFlow.collectAsState(initial = 10)

                //how to collect stateflow:
                val count = viewModel.stateFlow.collectAsState()

                Box(modifier = Modifier.fillMaxSize()) {

                    Text(
                        text = timer.value.toString(),
                        fontSize = 30.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    Button(
                        onClick = viewModel::increment,
                        modifier = Modifier
                            .padding(bottom = 25.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Text(text = count.value.toString())
                    }

                }
            }
        }
    }
}



/*

//inside activity or fragment:
fun setupObservers(){

    //How to collect state flow in Xml based ui.
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            mainViewModel.stateFlow.collectLatest { number ->
                //binding.textView.text = number.toString()
            }
        }
    }

    //or use extension function(a simpler way):
    collectLatestLifecycleFlow(mainViewModel.stateFlow) { number ->
        //binding.textView.text = number.toString()
    }

    collectLatestLifecycleFlow(viewModel.stateFlow, ::initStateFlowResult)

}
fun initStateFlowResult(number: Int) {
    //binding.textView.text = number.toString()
}



//Extension function for collecting flows.
//Note : change ComponentActivity to AppCompatActivity in xml base projects.
fun <T> ComponentActivity.collectLatestLifecycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }
}


*/