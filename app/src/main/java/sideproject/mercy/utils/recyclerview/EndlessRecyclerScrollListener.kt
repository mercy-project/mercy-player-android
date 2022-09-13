package sideproject.mercy.utils.recyclerview

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class EndlessRecyclerScrollListener(
    private var loadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var loading = true
    private var previousTotal = 0
    private var visibleThreshold = 5
    private var firstVisibleItem by Delegates.notNull<Int>()
    private var visibleItemCount by Delegates.notNull<Int>()
    private var totalItemCount by Delegates.notNull<Int>()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return

        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager.itemCount
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            loadMore()
            loading = true
        }
    }

    fun refresh() {
        previousTotal = 0
        loading = true
    }
}