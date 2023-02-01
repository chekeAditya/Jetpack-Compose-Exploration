package com.app.testingapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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


@Composable
fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.background) {
        Text(
            text = "Hello $name",
            modifier = Modifier.padding(all = 24.dp)
        )
    }
}

//This will act like a component here
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.primary) {
        Greeting(name = "Aditya")
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    Surface(color = MaterialTheme.colors.primary) {
        Greeting(name = "Aditya")
    }
}