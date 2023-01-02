package com.project.oplevapp.ui.view.shareboard

import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import com.project.oplevapp.ui.Utility.*
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.oplevapp.R
import com.project.oplevapp.ui.theme.OplevAppTheme

class ShareboardsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OplevAppTheme {
                Surface() {
                    Messaging(shareBoardMessages = Messages.DataConversation.ideaMessages)
                }
            }
        }
    }

    @Composable
    fun MessageBox(messageIdea: ShareBoardMessage) {
        Box {
            Row(modifier = Modifier.padding(all = 15.dp).fillMaxWidth()) {
                Image(painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .border(1.dp, MaterialTheme.colors.secondary))
                Spacer(modifier = Modifier.width(10.dp))

                var readMore by remember {
                    mutableStateOf(false)
                }
                val color by animateColorAsState(
                    if (readMore) {
                        MaterialTheme.colors.primary
                    } else {
                        MaterialTheme.colors.surface
                    },
                )
                Column(
                    modifier = Modifier.clickable(onClick = {
                        readMore = !readMore
                    }),
                ){
                    Text(text = messageIdea.author)
                    Surface(color = color) {
                        Text(text = messageIdea.body,
                            maxLines = if (!readMore) {
                                1
                            } else {
                                Int.MAX_VALUE
                            }
                        )
                    }
                }
            }
        }
    }


    @Composable
    fun Messaging(shareBoardMessages: List<ShareBoardMessage>) {
        LazyColumn { items(shareBoardMessages) { MessageBox(messageIdea = it) } }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        OplevAppTheme {
            Surface {
                Messaging(shareBoardMessages = Messages.DataConversation.ideaMessages)
            }
        }
    }
}



