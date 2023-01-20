package com.project.oplevapp.view.ui.screen.idea_portal.actions

import androidx.compose.ui.focus.FocusState
import com.project.oplevapp.model.data.utils.Utils
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Idea
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.IdeaException
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.IdeaSequence
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Sequence

class CreatingIdea {
    private val utils: Utils
    constructor(utils: Utils) {
        this.utils = utils
    }

    @Throws(IdeaException::class)
    suspend operator fun invoke(idea: Idea) {
        if(idea.ideaTitle?.isBlank() == true) throw IdeaException("Titlen skal udfyldes.")
        if(idea.ideaSuggestionText?.isBlank() == true) throw IdeaException("Der skal skrives noget.")
        utils.typeIdeaMessage(idea)
    }
}

class DeleteIdea(private val utils: Utils) {
    suspend operator fun invoke(idea: Idea) {
        utils.deleteIdeaMessage(idea)
    }
}

class LoadIdea(private val utils: Utils) {
    suspend operator fun invoke(id: Int): Idea? {
        return utils.getIdeaMessagesByID(id)
    }
}

class LoadIdeaMessages(private val utils: Utils) {
    operator fun invoke(ideaSequence: IdeaSequence = IdeaSequence.IdeaTimeCreated(Sequence.Sequences)): Flow<List<Idea>> {
        return utils.ideaMessages.map { idea ->
            when(ideaSequence.sequence) {
               is Sequence.Sequences -> {
                    when(ideaSequence) {
                        is IdeaSequence.IdeaTitle -> idea.sortedByDescending { it.ideaTitle?.lowercase() }
                        is IdeaSequence.IdeaTimeCreated -> idea.sortedByDescending { it.ideaTimeCreated }
                        is IdeaSequence.IdeaColorStatus -> idea.sortedByDescending { it.ideaColorStatus }
                    }
                }
            }
        }
    }
}

sealed class IdeaActions {
    data class TitleTyped(val titleTyped: String): IdeaActions()
    data class ModifyTitleTyped(val modifyTitle: FocusState): IdeaActions()
    data class IdeaMessageTyped(val ideaMessage: String): IdeaActions()
    data class ModifyIdeaMessageTyped(val modifyIdea: FocusState): IdeaActions()
    data class ColorSelector(val color: Int): IdeaActions()
    object SavingObject: IdeaActions()
}