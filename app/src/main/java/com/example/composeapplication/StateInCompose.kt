package com.example.composeapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composeapplication.ui.theme.ComposeApplicationTheme


@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    //A stateful composable owns state. like the count state below:
    var count by rememberSaveable {
        mutableStateOf(0)
    }
    StatelessCounter(
        count = count,
    ) {
        count++
    }
}

@Composable
private fun StatelessCounter(
    modifier: Modifier = Modifier,
    count: Int,
    onIncrement: () -> Unit,
) {
    //A stateless composable doesn't hold any state.
    Column(modifier = modifier.padding(16.dp)) {
        //Save state in configuration changes(like saveStateHandle)
        if (count > 0)
            Text(text = "You've had $count glasses")
        Button(
            onClick = onIncrement,
            enabled = count < 10,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.error,
                contentColor = Color.Green,
                disabledBackgroundColor = Color.LightGray,
                disabledContentColor = Color.Gray
            ),
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Add one")
        }
    }
}


//@Preview
@Composable
fun StatefulCounterPreview() {
    ComposeApplicationTheme {
        StatefulCounter()
    }
}

//-----------------------------------------

data class WellnessTask(val id: Int, val label: String, var isChecked: Boolean = false)


@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    Column(modifier = modifier) {
        StatefulCounter()
        WellnessItemsList(
            tasks = viewModel.list,
            onCheckChanged = { task ->
                viewModel.checkChanged(task)
            },
            onClose = { task ->
                viewModel.removeTask(task)
            }
        )
    }
}

@Composable
fun StatelessWellnessItem(
    modifier: Modifier = Modifier,
    task: WellnessTask,
    onCheckChanged: (task: WellnessTask) -> Unit,
    onClose: (task: WellnessTask) -> Unit,
) {
    Row(
        modifier = modifier
            .background(Color.LightGray)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = task.label
        )
        Checkbox(
            checked = task.isChecked,
            onCheckedChange = {
                onCheckChanged(task)
            }
        )

        Icon(imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier.clickable {
                onClose(task)
            }
        )

    }
}

@Composable
fun WellnessItemsList(
    modifier: Modifier = Modifier,
    tasks: List<WellnessTask>,
    onCheckChanged: (task: WellnessTask) -> Unit,
    onClose: (task: WellnessTask) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(
            items = tasks,
            key = { task -> task.id }
        ) { task ->
            StatelessWellnessItem(
                modifier = modifier,
                task = task,
                onCheckChanged = { onCheckChanged(task) },
                onClose = onClose
            )
        }
    }
}


//@Preview
@Composable
fun WellnessItemPreview() {
    ComposeApplicationTheme {
        //StatefulWellnessTaskItem("Some name", {})
    }
}
