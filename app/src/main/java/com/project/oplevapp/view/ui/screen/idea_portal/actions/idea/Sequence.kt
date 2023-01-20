package com.project.oplevapp.view.ui.screen.idea_portal.actions.idea

sealed class Sequence {
    object Sequences: Sequence()
}

sealed class IdeaSequence(val sequence: Sequence) {
    class IdeaTitle(sequence: Sequence): IdeaSequence(sequence)
    class IdeaTimeCreated(sequence: Sequence): IdeaSequence(sequence)
    class IdeaColorStatus(sequence: Sequence): IdeaSequence(sequence)
}

sealed class IdeaActions {
    data class Sequence(val ideaSequence: IdeaSequence): IdeaActions()
    data class IdeaDeletion(val idea: Idea): IdeaActions()
    object Restore: IdeaActions()
}

data class IdeaStatus(
    val ideas: List<Idea> = emptyList(),
    val ideaSequence: IdeaSequence = IdeaSequence.IdeaTimeCreated(Sequence.Sequences),
    val isShown: Boolean = false
)

