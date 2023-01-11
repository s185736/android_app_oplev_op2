package com.project.oplevapp.ui.screen.idea_portal.actions.idea

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.oplevapp.R
import com.project.oplevapp.nav.Screen
import com.project.oplevapp.ui.screen.idea_portal.components.PortalSlots
import com.project.oplevapp.ui.screen.idea_portal.actions.idea.IdeaActions.Restore
import com.project.oplevapp.ui.theme.LightRed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun PortalScreen(
    navController: NavController,
    viewModel: ViewModel = hiltViewModel()
) {
    val vmState: IdeaStatus = viewModel.state.value
    val foldState: ScaffoldState = rememberScaffoldState()
    val coroutine: CoroutineScope = rememberCoroutineScope()

    Image(
        painter = painterResource(id = R.drawable.blue1),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.ModifyInIdeaMessageScreen.route) },
                    backgroundColor = LightRed
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Opret en ide.")
                }
            },
            scaffoldState = foldState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.oplev_dark_blue))
                    .padding(7.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ide Portalen",
                        style = MaterialTheme.typography.h2,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
                this.AnimatedVisibility(
                    visible = vmState.isShown,
                    enter = fadeIn().plus(slideInHorizontally()),
                    exit = fadeOut().plus(slideOutHorizontally())
                ) {
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(modifier = Modifier.fillMaxSize()) {

                    /*Using Grids for the cells,
                    and structuring the screen, we've set 1 ideas in one line.*/
                    LazyVerticalGrid(
                        cells = GridCells.Fixed(1)
                    ) {
                        items(vmState.ideas) { idea ->
                            Box(
                                modifier = Modifier.padding(10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                PortalSlots(
                                    idea = idea,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            navController.navigate(
                                                Screen.ModifyInIdeaMessageScreen.route + "?ideaId=${idea.id}"
                                            )
                                        }
                                ) {
                                    viewModel.run { onAction(IdeaActions.IdeaDeletion(idea)) }
                                    coroutine.launch {
                                        val action: SnackbarResult =
                                            foldState.snackbarHostState.showSnackbar(
                                                message = "Ideen er slettet.",
                                                actionLabel = "Fortryd"
                                            )
                                        when {
                                            action != SnackbarResult.ActionPerformed -> {
                                            }
                                            else -> {
                                                viewModel.onAction(Restore)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
}