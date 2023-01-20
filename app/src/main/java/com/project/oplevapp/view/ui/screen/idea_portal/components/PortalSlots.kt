package com.project.oplevapp.view.ui.screen.idea_portal.components

import android.os.Build
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.*
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Idea
import com.project.oplevapp.view.ui.theme.LightRed
import java.util.*

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun PortalSlots(
    idea: Idea,
    modifier: Modifier = Modifier,
    roundness: Dp = 20.dp,
    ideaDeletion: () -> Unit
    ) {

    Box {
        Box(modifier = modifier) {
            Canvas(modifier = Modifier.matchParentSize()) {
                /*Implementing the square by using lineTo*/
                val square: Path = Path().also {
                    //First part of top
                    it.lineTo(size.width.minus(0), 0F)
                    it.lineTo(size.width, 0F)
                    //Continues until its a square.
                    it.lineTo(size.width, size.height)
                    it.lineTo(0F, size.height)
                    it.close()
                }
                clipPath(square) {
                    drawRoundRect(
                        size = size,
                        color = Color(idea.ideaColorStatus),
                        cornerRadius = CornerRadius(roundness.toPx())
                    )
                }
            }
            Box {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {
                    Text(
                        text = idea.ideaTitle.toString(),
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold,
                        color = Black,
                        maxLines = 1
                    )
                    Text(
                        text = idea.ideaSuggestionText.toString(),
                        style = MaterialTheme.typography.body1,
                        color = White,
                        maxLines = 7,
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        IconButton(
                            onClick = ideaDeletion,
                            modifier = Modifier.size(50.dp)) {
                            /*Using Icon for delete icon*/
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Slet Ide.",
                                tint = LightRed,
                            )
                        }
                    }
                }
            }
        }
    }
}