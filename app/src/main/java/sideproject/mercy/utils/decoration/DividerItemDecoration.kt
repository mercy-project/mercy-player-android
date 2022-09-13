package sideproject.mercy.utils.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import sideproject.mercy.R
import kotlin.math.roundToInt

class DividerItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val divider: Drawable =
        ContextCompat.getDrawable(context, R.drawable.list_divider)!!

    private var horizontalMargin: Int =
        context.resources.getDimensionPixelOffset(R.dimen.list_divider_margin)

    private val bounds = Rect()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (parent.layoutManager == null) {
            return
        }

        c.save()
        val left = horizontalMargin
        val right = parent.width - horizontalMargin

        parent.children.forEach { child ->
            parent.getDecoratedBoundsWithMargins(child, bounds)
            val top: Int = bounds.bottom + child.translationY.roundToInt()
            val bottom: Int = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(c)
        }
        c.restore()
    }
}