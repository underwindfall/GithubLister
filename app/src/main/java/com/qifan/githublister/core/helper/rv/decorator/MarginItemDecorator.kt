package com.qifan.githublister.core.helper.rv.decorator

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Qifan on 2019-08-11.
 */
class MarginItemDecorator(
    private val space: Space,
    private val decorator: Position = Position.ALL
) : RecyclerView.ItemDecoration() {

    private fun getSeparationSpaceTop(space: Space, isFirstItem: Boolean) = when {
        isFirstItem -> space.marginTop
        else -> space.top
    }

    private fun getSeparationSpaceBottom(space: Space, isLastItem: Boolean) = when {
        isLastItem -> space.marginBottom
        else -> space.bottom
    }

    private fun RecyclerView.isFirstItem(view: View): Boolean = getChildAdapterPosition(view) == 0
    private fun RecyclerView.isLastItem(view: View): Boolean = getChildAdapterPosition(view) == childCount - 1

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            when (decorator) {
                Position.VERTICAL, Position.ALL -> {
                    top = getSeparationSpaceTop(space, parent.isFirstItem(view))
                    bottom = getSeparationSpaceBottom(space, parent.isLastItem(view))
                }
                Position.HORIZONTAL -> {
                    left = space.left
                    right = space.right
                }
            }
        }
    }

    data class Space(
        val space: Int,
        val top: Int = space,
        val right: Int = space,
        val bottom: Int = space,
        val left: Int = space,
        val marginTop: Int = space,
        val marginBottom: Int = space
    )

    enum class Position {
        VERTICAL,
        HORIZONTAL,
        ALL
    }

}