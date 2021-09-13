package com.fabulouszanna.fabpokedex.other

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.content.ContextCompat
import java.util.*

fun retrievePokemonImage(context: Context, name: String): Bitmap {
    val assetsManager = context.assets
    val path = "img/${name.lowercase()}"
    val stream = assetsManager.open(path)
    return BitmapFactory.decodeStream(stream)
}

fun extractColorResourceFromType(context: Context, type: String): Int =
    ContextCompat.getColor(
        context,
        context.resources.getIdentifier(
            type.lowercase(Locale.ROOT),
            "color",
            context.packageName
        )
    )