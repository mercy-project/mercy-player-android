package sideproject.mercy.utils.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.clearItemDecoration() {
    repeat(itemDecorationCount) {
        removeItemDecorationAt(0)
    }
}