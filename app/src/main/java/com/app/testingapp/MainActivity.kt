package com.app.testingapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.testingapp.ui.theme.TestingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestingAppTheme {
                Surface {
                    TopicCard(Topic(R.drawable.ic_launcher_foreground, name = "Cars", 58), true)
                }
            }
        }
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
    TestingAppTheme {
        Surface {
            TopicCard(Topic(R.drawable.ic_launcher_foreground, name = "Cars", 58), true)
        }
    }
}

@Composable
fun TopicCard(topic: Topic, selected: Boolean) {
    //val radius = if (selected) 20.dp else 0.dp
    //for animate this change we have
    val radius by animateDpAsState(
        if (selected) 20.dp else 0.dp
    )
    Spacer(modifier = Modifier.padding(all = 50.dp))
    Card(
        shape = RoundedCornerShape(topStart = radius),
        backgroundColor = Color.Red,
    ) {
        Column {
            Row {
                Box {
                    Image(painterResource(id = topic.image), contentDescription = "Image one")
                    if (selected) {
                        Icon(Icons.Filled.Done, "Icon if it's selected")
                    }
                }
                Text(
                    text = topic.name,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun TopicList(topic: List<Topic>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(topic) { topic ->
//            TopicChip(topic)
        }
    }
}

data class Topic(
    val image: Int,
    val name: String,
    val number: Int
)

data class TopicChip(
    val image: Int,
    val name: String,
    val number: Int
)