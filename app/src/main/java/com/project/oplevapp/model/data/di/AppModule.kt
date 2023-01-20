/*Source: https://www.geeksforgeeks.org/how-to-build-a-simple-note-android-app-using-mvvm-and-room-database*/
/*Source: https://stackoverflow.com/questions/71631944/how-make-appmodule-works-in-dagger-2-with-kotlin-app*/
/*Source: https://medium.com/@sfazleyrabbi/dagger-android-beginner-to-intermediate-guide-cd26aa91b7c2*/
package com.project.oplevapp.model.data.di

import android.app.Application
import androidx.room.Room
import com.project.oplevapp.view.ui.screen.idea_portal.actions.*
import com.project.oplevapp.model.data.db.RoomDB
import com.project.oplevapp.model.data.utils.Utils
import com.project.oplevapp.model.data.utils.UtilsDAO
import com.project.oplevapp.view.ui.screen.idea_portal.actions.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun ideaDatabase(application: Application): RoomDB {
        return Room.databaseBuilder(application, RoomDB::class.java, RoomDB.DB_NAME).build()
    }

    @Provides
    @Singleton
    fun ideaResources(roomDb: RoomDB): Utils = UtilsDAO(roomDb.dataAccessObject)

    @Provides
    @Singleton
    fun Utils.ideaActions(): MainActions {
        return MainActions(
            getIdeaMessages = LoadIdeaMessages(this),
            delIdea = DeleteIdea(this),
            addIdea = CreatingIdea(this),
            getIdea = LoadIdea(this)
        )
    }
}
