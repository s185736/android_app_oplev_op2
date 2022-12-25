package com.project.oplevapp.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.oplevapp.R
import com.project.oplevapp.ui.theme.OplevAppTheme

class ShareboardsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OplevAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(messages = SampleData.conversationSample)

                }
            }
        }
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(msg: Message) {
        Row(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            )

            Spacer(modifier = Modifier.width(8.dp))


            var isExpanded by remember { mutableStateOf(false) }

            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }

    object SampleData {
        private const val companyName: String = "OPLEV"
        val conversationSample = listOf(
            Message(
                companyName, "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName, """Vi kan spise:
                    |Kebab
                    |Kebab
                    |Kebab
                    |Kebab
                    |Kebab
                    |Kebab
                    |Kebab
                """.trimMargin()
            ), Message(
                companyName,
                "Jeg vil gerne spise Kebab2." + "Haha, jeg laver sjov!"
            ), Message(
                companyName, "Jeg vil gerne spise Kebab3."
            ), Message(
                companyName, """Jeg vil gerne spise Kebab1.
                    |og Kebab igen...
                    |og igen.
                    |og igen...
                """.trimMargin()
            ), Message(
                companyName, "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName,
                "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName, "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName, "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName,
                "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName,
                "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName, "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName, "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName,
                "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName, "Jeg vil gerne spise Kebab1."
            ), Message(
                companyName, "Jeg vil gerne spise Kebab1."
            )
        )
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message -> MessageCard(msg = message) }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        OplevAppTheme {
            Surface {
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }
}



