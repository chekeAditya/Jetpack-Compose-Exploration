package com.app.testingapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
                    MessageCard(Message(author = "Aditya", "I've wrote many articles and books"))
                }
            }
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 10.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = msg.description,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Black)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            msg.author?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 5.dp) {
                msg.body?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colors.onSecondary,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 5.dp)
                    )
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
            MessageCard(Message(author = "Aditya", "I've wrote many articles and books"))
        }
    }
}

data class Message(
    val author: String? = null,
    val body: String? = null,
    val description: String? = null
)