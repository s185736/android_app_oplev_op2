package com.project.oplevapp.ui.screen.idea_portal.actions.idea

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.oplevapp.ui.theme.SquareColorOne
import com.project.oplevapp.ui.theme.SquareColorThree
import com.project.oplevapp.ui.theme.SquareColorTwo


class IdeaException(message: String): Exception(message)

@SuppressLint("ParcelCreator")
@Entity
data class Idea(
        val ideaTitle: String?, //displayed
        val ideaSuggestionText: String?, //displayed
        val ideaTimeCreated: Long, //will not be displayed.
        val ideaColorStatus: Int, //displayed
        @PrimaryKey val id: Int ?= null) : Parcelable {
            constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readLong(),
            parcel.readInt(),
            parcel.readValue(Int::class.java.classLoader) as? Int
            )

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

    /*Automatically generated..*/
    override fun describeContents(): Int = 0
    /*Automatically generated..*/
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(ideaTitle)
        dest.writeString(ideaSuggestionText)
        dest.writeLong(ideaTimeCreated)
        dest.writeInt(ideaColorStatus)
        dest.writeValue(id)
    }
}