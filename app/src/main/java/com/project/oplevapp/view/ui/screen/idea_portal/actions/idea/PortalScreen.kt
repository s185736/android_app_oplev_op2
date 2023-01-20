package com.project.oplevapp.view.ui.screen.idea_portal.actions.idea

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.R
import com.project.oplevapp.view.ui.nav.Screen
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.IdeaActions.Restore
import com.project.oplevapp.view.ui.screen.idea_portal.components.PortalSlots
import com.project.oplevapp.view.ui.theme.LightRed
import com.project.oplevapp.view.ui.theme.OplevDarkBlue
import com.project.oplevapp.view.ui.theme.WhiteBackground
import com.project.oplevapp.viewmodel.IdeaViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun PortalScreen(
    navController: NavController,
    ideaViewModel: IdeaViewModel = hiltViewModel()
)  {
    var db = Firebase.firestore.collection("ideaPortal")
    var ideas = remember {
        mutableStateListOf<Idea>()
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    val vmState: IdeaStatus = ideaViewModel.state.value
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
                    .background(color = WhiteBackground)
                    .padding(7.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.padding(10.dp).clickable { navController.popBackStack() },
                    )
                    Text(
                        text = "Ide Portalen",
                        style = MaterialTheme.typography.h2,
                        color = OplevDarkBlue,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center
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


               /* Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()


                    try {
                        db.addSnapshotListener { snapshot, e ->
                            if (snapshot != null) {
                                for (document in snapshot) {

                                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                                    val id = document.id
                                    val ideaTitle = document.data["title"] as String
                                    val ideaSuggestionText = document.data["text"] as String
                                    val ideaTimeCreated = document.data["time"] as Long
                                    val ideaColorStatus = document.data["color"] as Int
                                    CoroutineScope(Dispatchers.IO).launch {
                                        ideas.add(//mainActions.addIdea(
                                            Idea(
                                                id = id.toInt(),
                                                ideaTitle = ideaTitle,
                                                ideaSuggestionText = ideaSuggestionText,
                                                ideaTimeCreated = ideaTimeCreated,
                                                ideaColorStatus = ideaColorStatus
                                            )
                                        )
                                    }

                                }
                                isLoading = false
                            } else {
                                if (e != null) {
                                    println(e.message)
                                    Log.w(ContentValues.TAG, "Error getting documents.", e)
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.w(ContentValues.TAG, "Error getting documents.", e)
                    }
                }*/
                    /*Using Grids for the cells,
                    and structuring the screen, we've set 1 ideas in one line.*/
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1)
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
                                    ideaViewModel.run { onAction(IdeaActions.IdeaDeletion(idea)) }
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
                                                ideaViewModel.onAction(Restore)
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