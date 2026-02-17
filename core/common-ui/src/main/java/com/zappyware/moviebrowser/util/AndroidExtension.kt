package com.zappyware.moviebrowser.util

import android.content.Context
import android.content.pm.ApplicationInfo

fun Context.isDebuggable() = 0 != (applicationContext.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)
