/*Source: https://www.geeksforgeeks.org/how-to-build-a-simple-note-android-app-using-mvvm-and-room-database*/
package com.project.oplevapp.model.data.utils

import com.project.oplevapp.model.interfaces.DataAccessObject
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Idea
import kotlinx.coroutines.flow.Flow

class UtilsDAO : Utils {

    private val dataAccessObject: DataAccessObject

    constructor(dataAccessObject: DataAccessObject) {
        this.dataAccessObject = dataAccessObject
    }

    override val ideaMessages: Flow<List<Idea>>
        get() {
            return dataAccessObject.getIdeaMessages()
        }

    override suspend fun getIdeaMessagesByID(id: Int): Idea? {
        return dataAccessObject.getIdeaMessagesByID(id)
    }

    override suspend fun typeIdeaMessage(idea: Idea) {
        dataAccessObject.typeIdeaMessage(idea)
    }

    override suspend fun deleteIdeaMessage(idea: Idea) {
        dataAccessObject.deleteIdeaMessage(idea)
    }
}

interface Utils {

    val ideaMessages: Flow<List<Idea>>

    suspend fun getIdeaMessagesByID(id: Int): Idea?

    suspend fun typeIdeaMessage(idea: Idea)

    suspend fun deleteIdeaMessage(idea: Idea)

}
