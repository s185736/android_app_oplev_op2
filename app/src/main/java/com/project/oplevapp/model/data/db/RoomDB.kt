/*Source: https://www.geeksforgeeks.org/how-to-build-a-simple-note-android-app-using-mvvm-and-room-database*/
/*Source: https://developer.android.com/reference/android/arch/persistence/room/Database*/
/*Source: https://developer.android.com/reference/androidx/room/Database*/
package com.project.oplevapp.model.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.oplevapp.model.interfaces.DataAccessObject
import com.project.oplevapp.view.ui.screen.idea_portal.actions.idea.Idea

@Database(entities = [Idea::class], version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract val dataAccessObject: DataAccessObject

    companion object{
        const val DB_NAME = "idea_portal"
    }


}