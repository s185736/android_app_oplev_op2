/*Source: https://www.geeksforgeeks.org/how-to-build-a-simple-note-android-app-using-mvvm-and-room-database*/
/*Source: https://developer.android.com/reference/android/arch/persistence/room/Dao*/
/*Source: https://johncodeos.com/how-to-use-room-in-android-using-kotlin*/
package com.project.oplevapp.model.interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Idea
import kotlinx.coroutines.flow.Flow

@Dao
interface DataAccessObject {

    @Insert(onConflict = REPLACE)
    suspend fun typeIdeaMessage(idea: Idea)

    @Query("SELECT * FROM idea")
    fun getIdeaMessages(): Flow<List<Idea>>

    @Query("SELECT * FROM idea WHERE id = :id")
    suspend fun getIdeaMessagesByID(id: Int): Idea?

    @Delete
    suspend fun deleteIdeaMessage(idea: Idea)
}