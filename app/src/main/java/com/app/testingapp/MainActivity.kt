package com.app.testingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.testingapp.ui.theme.TestingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingAppTheme {
                MyApp()
            }
        }
    }
}

//This will act like a component here
@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose", "Adtya")
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Surface(
        color = MaterialTheme.colors.secondary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        val expanded = remember { mutableStateOf(false) }
        val extraPadding = if (expanded.value) 48.dp else 0.dp
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
                /*.fillMaxWidth()*/
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Preview(name = "Light Mode", widthDp = 320)
@Composable
fun PreviewMessageCard() {
    MyApp()
}