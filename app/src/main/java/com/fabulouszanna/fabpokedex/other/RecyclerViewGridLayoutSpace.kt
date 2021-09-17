package com.fabulouszanna.fabpokedex.other

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewGridLayoutSpace(
    private val includeEdge: Boolean,
    private val spacing: Int = 8,
    private val spanCount: Int = 2
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
//        super.getItemOffsets(outRect, view, parent, state)
//        outRect.set(
//            spacing.toPx(),
//            spacing.toPx(),
//            spacing.toPx(),
//            spacing.toPx(),
//        )
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing.toPx() - column * spacing.toPx() / spanCount
            outRect.right = (column + 1) * spacing.toPx() / spanCount

            if (position < spanCount) { // top edge
                outRect.top = spacing.toPx()
            }
            outRect.bottom = spacing.toPx()
        } else {
            outRect.left = column * spacing.toPx() / spanCount
            outRect.right = spacing.toPx() - (column + 1) * spacing.toPx() / spanCount

            if (position >= spanCount) {
                outRect.top = spacing.toPx()
            }
        }
    }
}