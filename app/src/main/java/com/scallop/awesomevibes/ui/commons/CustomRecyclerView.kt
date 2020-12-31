package com.scallop.awesomevibes.ui.commons

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class CustomRecyclerView(context: Context) : RecyclerView(context) {

    fun getlayout() {
        this.layoutManager
    }

    fun setLoadMore() {
        val typedLayoutManager = when (this.layoutManager) {
            is GridLayoutManager -> layoutManager as GridLayoutManager
            is StaggeredGridLayoutManager -> layoutManager as StaggeredGridLayoutManager
            is LinearLayoutManager -> layoutManager as LinearLayoutManager
            else -> layoutManager as LinearLayoutManager
        }
        addOnScrollListener(object : EndlessRecyclerViewScrollListener(
            typedLayoutManager as LinearLayoutManager,
            0
        ) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            }
        })
    }
}