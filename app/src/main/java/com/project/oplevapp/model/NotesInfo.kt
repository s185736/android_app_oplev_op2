package com.project.oplevapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
    class NotesInfo(
        val id: String?,
        var text: String,

        ) : Parcelable
