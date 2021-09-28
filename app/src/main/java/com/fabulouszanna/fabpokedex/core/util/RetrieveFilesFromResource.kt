package com.fabulouszanna.fabpokedex.core.util

import android.content.Context
import androidx.core.content.ContextCompat
import java.util.*

fun retrieveDrawableFromName(context: Context, drawableName: String): Int =
    context.resources.getIdentifier(
        drawableName,
        "drawable",
        context.packageName
    )

fun retrieveColorResourceFromType(context: Context, type: String): Int =
    ContextCompat.getColor(
        context,
        context.resources.getIdentifier(
            type.lowercase(Locale.ROOT),
            "color",
            context.packageName
        )
    )