package com.project.oplevapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Country(
    val id: String?,
    var country: String,
    var city: String,
    var info: String,
    var departureDate: String,
    var returnDate: String,
    val imageUrl: String?,
) : Parcelable



val Denmark = Country(
    id = "",
    country = "Denmark",
    city = "Copenhagen",
    info = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse ornare, tortor et fringilla sagittis, risus magna convallis magna, et rutrum magna eros et tellus. Aenean ut neque arcu. Aenean lacinia nisl eget nisi posuere rutrum. Nam blandit faucibus ipsum id lobortis. Ut cursus velit at eros lacinia, vel condimentum nisi sollicitudin. Mauris in pharetra nisl, nec vulputate tellus. Maecenas tincidunt scelerisque nisl, ut porta ipsum. Sed consectetur laoreet convallis. Fusce fermentum, velit sit amet porttitor pretium, purus tortor porta ante, non lobortis eros nulla at sapien. Aenean efficitur ex condimentum metus varius viverra. Etiam a aliquet sem. Ut aliquam leo nec elit facilisis laoreet.",
    departureDate = "23/02-2023",
    returnDate = "30/02-2023",
    imageUrl = null
)





