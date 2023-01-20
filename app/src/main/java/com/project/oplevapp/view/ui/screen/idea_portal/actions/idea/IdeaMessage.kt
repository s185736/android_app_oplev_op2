package com.project.oplevapp.view.ui.screen.idea_portal.actions.idea

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.oplevapp.view.ui.theme.SquareColorOne
import com.project.oplevapp.view.ui.theme.SquareColorThree
import com.project.oplevapp.view.ui.theme.SquareColorTwo


class IdeaException(message: String): Exception(message)

@SuppressLint("ParcelCreator")
@Entity
data class Idea(
        val ideaTitle: String?,
        val ideaSuggestionText: String?,
        val ideaTimeCreated: Long,
        val ideaColorStatus: Int,
        @PrimaryKey val id: Int ?= null){

    /*Colors that can be chosen for created idea.
    * Purpose: To make it look colorful and different..*/
    companion object {
        val ideaColors: List<Color>
            get() = listOf(
                SquareColorOne,
                SquareColorTwo,
                SquareColorThree
            )
    }
}