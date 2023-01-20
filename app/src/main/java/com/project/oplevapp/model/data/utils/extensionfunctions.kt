package com.project.oplevapp.model.data.utils

import android.content.Context
import android.widget.Toast
//fra https://github.com/nameisjayant/Jetpack-Compose-Firebase

fun Context.showMsg(
    msg:String,
    duration:Int = Toast.LENGTH_SHORT
) = Toast.makeText(this,msg,duration).show()