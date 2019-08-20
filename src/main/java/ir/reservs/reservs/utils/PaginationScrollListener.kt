package ir.reservs.reservs.utils

import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val manager: LinearLayoutManager)
    : RecyclerView.OnScrollListener() {
    /*override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }*/

    private var isScrolling = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true;
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val currentItems = manager.childCount
        val totalItems = manager.itemCount
        val scrollOutItems = manager.findFirstVisibleItemPosition()
        if (isLastPage() || isLoading()) {
            return
        }
        if (check(currentItems, scrollOutItems, totalItems)) {
            isScrolling = false
            loadMoreItems()
        }
    }

    private fun check(currentItems: Int, scrollOutItems: Int, totalItems: Int): Boolean {
        return isScrolling && (currentItems + scrollOutItems == totalItems) && scrollOutItems > 0
    }

    abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}