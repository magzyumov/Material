package ru.magzyumov.material.ui.custom_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AutofitRecyclerView: RecyclerView {
    private var manager: CenteredGridLayoutManager? = null
    private var columnWidth: Int = -1

    constructor(context: Context): super(context) { init(context, null) }
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) { init(context, attrs) }
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle) { init(context, attrs) }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val attrsArray = intArrayOf(
                    android.R.attr.columnWidth
            )
            val array: TypedArray = context.obtainStyledAttributes(attrs, attrsArray)
            columnWidth = array.getDimensionPixelSize(0, -1)
            array.recycle()
        }
        manager = CenteredGridLayoutManager(getContext(), 1)
        layoutManager = manager
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        if (columnWidth > 0) {
            val spanCount = Math.max(1, measuredWidth / columnWidth)
            manager?.apply {
                this.spanCount = spanCount
            }
        }
    }

    inner class CenteredGridLayoutManager: GridLayoutManager {
        constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {}
        constructor(context: Context?, spanCount: Int): super(context, spanCount) {}
        constructor(context: Context?, spanCount: Int, orientation: Int, reverseLayout: Boolean): super(context, spanCount, orientation, reverseLayout) {}

        override fun getPaddingLeft(): Int {
            val totalItemWidth: Int = columnWidth * spanCount
            return if (totalItemWidth >= this@AutofitRecyclerView.measuredWidth) {
                super.getPaddingLeft() // do nothing
            } else {
                Math.round(this@AutofitRecyclerView.measuredWidth / (1f + spanCount) - totalItemWidth / (1f + spanCount)).toInt()
            }
        }

        override fun getPaddingRight(): Int {
            return paddingLeft
        }
    }

}