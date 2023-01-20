/*Source: https://www.geeksforgeeks.org/how-to-build-a-simple-note-android-app-using-mvvm-and-room-database*/
/*Source: https://www.geeksforgeeks.org/mvvm-model-view-viewmodel-architecture-pattern-in-android*/
package com.project.oplevapp.viewmodel

import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.project.oplevapp.model.repository.IdeaRepository
import com.project.oplevapp.view.ui.screen.idea_portal.actions.MainActions
import com.project.oplevapp.view.ui.screen.idea_portal.actions.MessageField
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.*
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Sequence
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class IdeaViewModel @Inject constructor(mainActions: MainActions) : ViewModel() {
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
class ModifyIdeaViewModel @Inject constructor(
    private val mainActions: MainActions, savedStates: SavedStateHandle) : ViewModel(), Parcelable {
    private val ideaColorBackground = mutableStateOf(Idea.ideaColors.random().toArgb())
    private val ideaSharedFlow = MutableSharedFlow<UserInterfaceAction>()
    private var getIdeaID: Int? = null
    val ideaRepository = IdeaRepository()
    var db = Firebase.firestore.collection("ideaPortalen")

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

    val ideaSave = Idea(
        ideaTitle = ideaTitle.value.message,
        ideaSuggestionText = ideaMessage.value.message,
        ideaTimeCreated = System.currentTimeMillis(),
        ideaColorStatus = ideaColor.value,
        id = getIdeaID
    )

    constructor(parcel: Parcel) : this(
        TODO("mainActions"),
        TODO("savedStates")
    ) {
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

    fun onAction(action: com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions) {
        when (action) {
            is com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions.TitleTyped -> {
                ideaTitleField.value = ideaTitle.value.copy(
                    message = action.titleTyped
                )
            }
            is com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions.ModifyTitleTyped -> {
                ideaTitleField.value = ideaTitle.value.copy(
                    isVisible = !action.modifyTitle.isFocused &&
                            ideaTitle.value.message.isBlank()
                )
            }
            is com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions.IdeaMessageTyped -> {
                ideaMessageField.value = ideaMessageField.value.copy(
                    message = action.ideaMessage
                )
            }
            is com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions.ModifyIdeaMessageTyped -> {
                ideaMessageField.value = ideaMessageField.value.copy(
                    isVisible = !action.modifyIdea.isFocused &&
                            ideaMessageField.value.message.isBlank()
                )
            }
            is com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions.ColorSelector -> {
                ideaColorBackground.value = action.color
            }
            is com.project.oplevapp.view.ui.screen.idea_portal.actions.IdeaActions.SavingObject -> {
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
                        //ideaRepository.saveIdea(ideaSave, content)

                    } catch (e: IdeaException) {
                        ideaSharedFlow.emit(
                            UserInterfaceAction.ShowSnackBar(
                                message = e.message ?: "Ideen kunne ikke gemmmes."
                            )
                        )
                    }
                }
            }
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

    companion object CREATOR : Parcelable.Creator<ModifyIdeaViewModel> {
        override fun createFromParcel(parcel: Parcel): ModifyIdeaViewModel {
            return ModifyIdeaViewModel(parcel)
        }

        override fun newArray(size: Int): Array<ModifyIdeaViewModel?> {
            return arrayOfNulls(size)
        }
    }
}