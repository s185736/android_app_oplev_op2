package com.project.oplevapp.view.ui.screen.idea_portal.actions

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import com.project.oplevapp.view.ui.screen.idea_portal.components.TextField
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.model.repository.IdeaRepository
import com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions.ColorSelector
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Idea
import com.project.oplevapp.view.ui.theme.LightRed
import com.project.oplevapp.viewmodel.ModifyIdeaViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnrememberedMutableState")
@Composable
fun ModifyPortal(navController: NavController, ideaColor: Int, viewModel: ModifyIdeaViewModel = hiltViewModel()) {
    val stateTitle: MessageField = viewModel.ideaTitle.value
    val stateIdea: MessageField = viewModel.ideaMessage.value
    val stateScaff: ScaffoldState = rememberScaffoldState()
    val ideaRepository = IdeaRepository()
    val content = LocalContext.current
    var getIdeaID: Int? = null
    var db = Firebase.firestore.collection("ideaPortalen")

    val ideaTitleField = mutableStateOf(MessageField(slot = "Skriv en kort titel."))
    val ideaMessageField = mutableStateOf(MessageField(slot = "Hvad har du i tankerne?"))
    val ideaColorBackground = mutableStateOf(Idea.ideaColors.random().toArgb())
    val ideaSharedFlow = MutableSharedFlow<ModifyIdeaViewModel.UserInterfaceAction>()

    val ideaTitle: State<MessageField> = ideaTitleField
    val ideaMessage: State<MessageField> = ideaMessageField
    val ideaColors: State<Int> = ideaColorBackground

    val ideaSave = Idea(
        ideaTitle = ideaTitle.value.message,
        ideaSuggestionText = ideaMessage.value.message,
        ideaTimeCreated = System.currentTimeMillis(),
        ideaColorStatus = ideaColors.value,
        id = getIdeaID
    )

    var ideas = remember {
        mutableStateListOf<Idea>()
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    val background = remember<Animatable<Color, AnimationVector4D>> {
        Animatable(
            initialValue = Color(
                color = when {
                    ideaColor != -1 -> {
                        ideaColor
                    }
                    else -> {
                        viewModel.ideaColor.value
                    }
                }
            )
        )
    }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) {
        viewModel.ideaFlow.collectLatest { action ->
            when (action) {
                is ModifyIdeaViewModel.UserInterfaceAction.ShowSnackBar ->
                    stateScaff.snackbarHostState.showSnackbar(
                        message = action.message
                    )
                is ModifyIdeaViewModel.UserInterfaceAction.SaveObject -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Box {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            viewModel.onAction(IdeaActions.SavingObject)
                            ideaRepository.saveIdea(ideaSave,content)
                        },
                        backgroundColor = LightRed
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Gem din ide.")
                    }
                },
                scaffoldState = stateScaff
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp).clickable { navController.popBackStack() },
                )
                Column(

                    modifier = Modifier.fillMaxSize().background(background.value).padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Idea.ideaColors.forEach {
                            val colorID: Int = it.toArgb()
                            Box(
                                modifier = Modifier
                                    //.size(45.dp)
                                    .height(25.dp)
                                    .width(45.dp)
                                    .shadow(20.dp, RectangleShape)
                                    .clip(RectangleShape)
                                    .background(it)
                                    .border(
                                        width = 5.dp,
                                        color =
                                        when (viewModel.ideaColor.value) {
                                            colorID -> {
                                                Color.Black
                                            }
                                            else -> {
                                                Color.Transparent
                                            }
                                        },
                                        shape = RectangleShape
                                    )
                                    .clickable {
                                        coroutineScope.launch {
                                            background.animateTo(
                                                targetValue = Color(colorID),
                                                animationSpec = tween(
                                                    durationMillis = 1000
                                                )
                                            )
                                        }
                                        viewModel.onAction(ColorSelector(colorID))
                                    }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    TextField(
                        value = stateTitle.message,
                        ideaMessage = stateTitle.slot,
                        onValueChange = { viewModel.onAction(IdeaActions.TitleTyped(it)) },
                        onFocusChange = { viewModel.onAction(IdeaActions.ModifyTitleTyped(it)) },
                        isVisible = stateTitle.isVisible,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.h4
                    )
                    Spacer(modifier = Modifier.height(20.dp))


                    TextField(
                        value = stateIdea.message,
                        ideaMessage = stateIdea.slot,
                        onValueChange = { viewModel.onAction(IdeaActions.IdeaMessageTyped(it)) },
                        onFocusChange = { viewModel.onAction(IdeaActions.ModifyIdeaMessageTyped(it)) },
                        isVisible = stateIdea.isVisible,
                        textStyle = MaterialTheme.typography.h5,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
    }
}