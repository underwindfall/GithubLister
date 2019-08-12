package com.qifan.githublister.core.helper.rv.scroll

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndLessScrollListener(private val onLoadMore: (Int) -> Unit) : RecyclerView.OnScrollListener() {
    private val mItemsOffset = 5
    private var mLastItemsCount = 0
    private var isLoading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val mLayoutManager = recyclerView.layoutManager
        val itemsCount = mLayoutManager?.itemCount
        if (mLayoutManager is LinearLayoutManager) {
            val lastVisibleItemIndex =
                ((recyclerView.layoutManager) as LinearLayoutManager).findLastVisibleItemPosition()
            if (itemsCount != null) {
                if (itemsCount < mLastItemsCount) {
                    this.mLastItemsCount = itemsCount
                    if (itemsCount == 0) {
                        isLoading = true
                    }
                }
                if (isLoading && itemsCount > mLastItemsCount) {
                    isLoading = false
                    mLastItemsCount = itemsCount
                }

                if (!isLoading && itemsCount > 0 && lastVisibleItemIndex + mItemsOffset > itemsCount) {
                    onLoadMore(itemsCount)
                    isLoading = true
                }
            }
        }
    }

}