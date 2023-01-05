package com.project.oplevapp.ui.screen.idea_portal.actions.idea

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.project.oplevapp.ui.screen.idea_portal.actions.MainActions
import com.project.oplevapp.ui.screen.idea_portal.actions.MessageField
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class ViewModel @Inject constructor(mainActions: MainActions) : ViewModel() {
    private val ideaActions: MainActions = mainActions

    private val mutState: MutableState<IdeaStatus> = mutableStateOf(IdeaStatus())
    val state: State<IdeaStatus>
    private var newlyIdeasOnTrash: Idea? = null
    private var getProcessDone: Job? = null

    fun onAction(action: IdeaActions) {
        when (action) {
            is IdeaActions.Sequence -> {
                when {
                    state.value.ideaSequence::class == action.ideaSequence::class &&
                            state.value.ideaSequence.sequence == action.ideaSequence.sequence
                    -> {
                        return
                    }
                    else -> getIdeaMessage(action.ideaSequence)
                }
            }
            is IdeaActions.IdeaDeletion -> viewModelScope.launch {
                with(ideaActions) { delIdea(action.idea) }
                action.idea.also { newlyIdeasOnTrash = it }
            }
            is IdeaActions.Restore -> {
                viewModelScope.launch {
                    with(ideaActions) {
                        addIdea(idea = newlyIdeasOnTrash ?: return@launch)
                    }
                    null.also { newlyIdeasOnTrash = it }
                }
            }
        }
    }

    private fun getIdeaMessage(ideaSequence: IdeaSequence) {
        this.getProcessDone?.cancel()
        ideaActions.getIdeaMessages(ideaSequence)
            .onEach {
                state.value.copy(
                    ideas = it,
                    ideaSequence = ideaSequence
                ).also { it -> mutState.value = it }
            }
            .launchIn(viewModelScope).also { getProcessDone = it }
    }

    init {
        this.state = mutState
        getIdeaMessage(IdeaSequence.IdeaTimeCreated(Sequence.Sequences))
    }
}

@HiltViewModel
class ModifyViewModel @Inject constructor(
    private val mainActions: MainActions, savedStates: SavedStateHandle) : ViewModel(), Parcelable {
    private val ideaColorBackground = mutableStateOf(Idea.ideaColors.random().toArgb())
    private val ideaSharedFlow = MutableSharedFlow<UserInterfaceAction>()
    private var getIdeaID: Int? = null
    private val ideaTitleField = mutableStateOf(

        MessageField(slot = "Skriv en kort titel.")
    )
    private val ideaMessageField = mutableStateOf(
        MessageField(slot = "Hvad har du i tankerne?")
    )
     val ideaTitle: State<MessageField> = ideaTitleField
     val ideaMessage: State<MessageField> = ideaMessageField
     val ideaColor: State<Int> = ideaColorBackground
    val ideaFlow = ideaSharedFlow.asSharedFlow()

    constructor(parcel: Parcel) : this(
        TODO("mainActions"),
        TODO("savedStates")) {
        getIdeaID = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    init {
        savedStates.get<Int>("ideaId")?.let { ideaId ->
            when {
                ideaId != -1 -> {
                    viewModelScope.launch {
                        mainActions.getIdea(ideaId)?.also { idea ->
                            getIdeaID = idea.id
                            ideaTitleField.value = ideaTitle.value.copy(
                                message = idea.ideaTitle.toString(),
                                isVisible = false
                            )
                            ideaMessageField.value = ideaMessageField.value.copy(
                                message = idea.ideaSuggestionText.toString(),
                                isVisible = false
                            )
                            ideaColorBackground.value = idea.ideaColorStatus
                        }
                    }
                }
                else -> {}
            }
        }
    }

    fun onAction(action: com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions) {
        when(action) {
            is com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions.TitleTyped -> {
                ideaTitleField.value = ideaTitle.value.copy(
                    message = action.titleTyped
                )
            }
            is com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions.ModifyTitleTyped -> {
                ideaTitleField.value = ideaTitle.value.copy(
                    isVisible = !action.modifyTitle.isFocused &&
                            ideaTitle.value.message.isBlank()
                )
            }
            is com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions.IdeaMessageTyped -> {
                ideaMessageField.value = ideaMessageField.value.copy(
                    message = action.ideaMessage
                )
            }
            is com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions.ModifyIdeaMessageTyped -> {
                ideaMessageField.value = ideaMessageField.value.copy(
                    isVisible = !action.modifyIdea.isFocused &&
                            ideaMessageField.value.message.isBlank()
                )
            }
            is com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions.ColorSelector -> {
                ideaColorBackground.value = action.color
            }
            is com.project.oplevapp.ui.screen.idea_portal.actions.IdeaActions.SavingObject -> {
                viewModelScope.launch {
                    try {
                        mainActions.addIdea(
                            Idea(
                                ideaTitle = ideaTitle.value.message,
                                ideaSuggestionText = ideaMessage.value.message,
                                ideaTimeCreated = System.currentTimeMillis(),
                                ideaColorStatus = ideaColor.value,
                                id = getIdeaID
                            )
                        )
                        ideaSharedFlow.emit(UserInterfaceAction.SaveObject)
                    } catch(e: IdeaException) {
                        ideaSharedFlow.emit(
                            UserInterfaceAction.ShowSnackBar(
                                message = e.message ?: "Ideen kunne ikke gemmmes."
                            )
                        )
                    }
                }
            }
            else -> {}
        }
    }

    sealed class UserInterfaceAction {
        data class ShowSnackBar(val message: String): UserInterfaceAction()
        object SaveObject: UserInterfaceAction()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(getIdeaID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModifyViewModel> {
        override fun createFromParcel(parcel: Parcel): ModifyViewModel {
            return ModifyViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ModifyViewModel?> {
            return arrayOfNulls(size)
        }
    }
}