package com.app.testingapp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
                    Conversation(SampleData.conversions())
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

        // We keep track if the message is expanded or not in this variable
        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.secondary,
        )
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
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
//                        color = MaterialTheme.colors.onSecondary,
                        //here i've to update the color
                        color = surfaceColor,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 5.dp),
                        // If the message is expanded, we display all its content otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    )
                }
            }
        }
    }
}

@Composable
fun Conversation(message: List<Message>) {
    LazyColumn {
        items(message) { message ->
            MessageCard(message)
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
            Conversation(SampleData.conversions())
        }
    }
}

data class Message(
    val author: String? = null,
    val body: String? = null,
    val description: String? = null
)


class SampleData {
    companion object {
        @Composable
        fun conversions(): List<Message> {
            return listOf(
                Message(
                    author = "Aditya",
                    "I've wrote many articles and books asdchkasdcj adscjnjajlsd adscnajsncdla  asdnclnalsdc asdlcna dcajlndcljasj casljc ajs dc ljadsjc ajdscl ajls cdjaljsdc ja scjljas clj asd"
                ),
                Message(author = "Karan", "I've wrote many artic aldscnals caljsdjc lajs cdj ajlsdc  ajlds ncj asnd cja sd la sdcl jasd cjl asdl ljasdl asdljv la sdv asdles"),
                Message(author = "Prachi", "How are you, how's life going ? ajsdcnjalsd casdc asdc asdc a dc asdc adsc adsc asdc asdc asdc adsc asdc asdc "),
                Message(
                    author = "Anjali",
                    "I am getting so bored will you wantcasdlkc asm,dc asd cads cads csad ca sdca sdc a dva fdb  gabdfsdfdagsgn bdags bsff dbsds gbgsd bfvffdv fdbvsfs abvsf sb vf sbvsf sbfs sfb dd bdfsv bv  be to entertain you"
                ),
                Message(
                    author = "Varun",
                    "Can you pcda dc ac fsvds cdsavfbvss sbgd bs fd sbgd sds bg dd bsfd f dbd sf sbsfd f dbf  bffs b dsfsbfdf d fdf d fdvdb vf fdv f vdb f vdfsbfvds lease tell me about yourself"
                ),
            )
        }
    }
}