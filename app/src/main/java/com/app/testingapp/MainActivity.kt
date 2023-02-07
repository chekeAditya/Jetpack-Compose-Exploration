package com.app.testingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.testingapp.ui.theme.TestingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

//This will act like a component here
@Composable
fun MyApp(modifier: Modifier = Modifier) {

//    var shouldShowOnboarding by remember { mutableStateOf(true) } // remember function works only as long as the composable is kept in the Composition
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnBoardingScreen(onContinueClick = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
    /*Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }*/
}

@Preview(name = "Light Mode", widthDp = 320)
@Composable
fun PreviewMessageCard() {
    TestingAppTheme {
        Greetings()
    }
}

@Composable
fun MyAppPreview() {
    TestingAppTheme {
        MyApp(Modifier.fillMaxSize())
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

@Composable
fun OnBoardingScreen(
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {

//    var shouldShowOnBoarding by remember { mutableStateOf(true) } //this will hold the callBack

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Welcome to the Basic's Codelab")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
//            onClick = { shouldShowOnBoarding = false }
            onClick = onContinueClick
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    TestingAppTheme {
        OnBoardingScreen(onContinueClick = {}) //Do nothing
    }
}