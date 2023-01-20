package com.project.oplevapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
    class NotesInfo(
        val id: String?,
        var text: String,

        ) : Parcelable
