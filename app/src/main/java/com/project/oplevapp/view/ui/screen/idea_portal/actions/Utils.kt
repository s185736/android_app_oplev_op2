package com.project.oplevapp.view.ui.screen.idea_portal.actions

import android.os.Parcel
import android.os.Parcelable

data class MessageField(
    val message: String = "",
    val slot: String = "",
    val isVisible: Boolean = true
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readByte() != 0.toByte())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
        parcel.writeString(slot)
        parcel.writeByte(if (isVisible) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MessageField> {
        override fun createFromParcel(parcel: Parcel): MessageField {
            return MessageField(parcel)
        }

        override fun newArray(size: Int): Array<MessageField?> {
            return arrayOfNulls(size)
        }
    }
}

data class MainActions(
    val addIdea: CreatingIdea,
    val getIdea: LoadIdea,
    val getIdeaMessages: LoadIdeaMessages,
    val delIdea: DeleteIdea
)