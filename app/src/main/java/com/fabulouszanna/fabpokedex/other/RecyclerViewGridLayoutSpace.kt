package com.fabulouszanna.fabpokedex.other

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewGridLayoutSpace : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(
            8.toPx(),
            8.toPx(),
            8.toPx(),
            8.toPx(),
        )
    }
}