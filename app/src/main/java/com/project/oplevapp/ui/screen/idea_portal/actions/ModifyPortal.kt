/*Source: https://m2.material.io/components/buttons-floating-action-button/android#regular-fabs*/
/*Source: */
package com.project.oplevapp.ui.screen.idea_portal.actions

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions.ColorSelector
import com.project.oplevapp.ui.screen.idea_portal.actions.idea.Idea
import com.project.oplevapp.ui.screen.idea_portal.components.TextField
import com.project.oplevapp.ui.screen.idea_portal.actions.idea.ModifyViewModel
import com.project.oplevapp.ui.theme.LightRed
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ModifyPortal(navController: NavController, ideaColor: Int, viewModel: ModifyViewModel = hiltViewModel()) {
    val stateTitle: MessageField = viewModel.ideaTitle.value
    val stateIdea: MessageField = viewModel.ideaMessage.value
    val stateScaff: ScaffoldState = rememberScaffoldState()

    val background = remember<Animatable<Color, AnimationVector4D>> {
        return@remember Animatable(
            initialValue = Color(color = when {
                !ideaColor.equals(-1) -> {
                    ideaColor
                }
                else -> {
                    viewModel.ideaColor.value }
            })
        )
    }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    LaunchedEffect(true) {
        viewModel.ideaFlow.collectLatest { action ->
            when (action) {
                is ModifyViewModel.UserInterfaceAction.ShowSnackBar ->
                    stateScaff.snackbarHostState.showSnackbar(
                            message = action.message
                        )
                is ModifyViewModel.UserInterfaceAction.SaveObject -> {
                    navController.navigateUp()
                }
            }
        }
    }
    Box {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { viewModel.onAction(IdeaActions.SavingObject) },
                    backgroundColor = LightRed) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "Gem din ide.")
                }
            },
            scaffoldState = stateScaff) {
            Column(
                modifier = Modifier.fillMaxSize().background(background.value).padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Idea.ideaColors.forEach {
                        val colorID: Int = it.toArgb()
                        Box(
                        modifier = Modifier.size(45.dp)
                            .shadow(20.dp, RectangleShape)
                            .clip(RectangleShape)
                            .background(it).border(
                                width = 5.dp, color =
                                when (viewModel.ideaColor.value) {
                                    colorID -> {
                                        Color.Black
                                    } else -> {
                                        Color.Transparent
                                    }
                                },
                                shape = RectangleShape
                            )
                            .clickable { coroutineScope.launch {
                                    background.animateTo(
                                        targetValue = Color(colorID),
                                        animationSpec = tween(durationMillis = 999)
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