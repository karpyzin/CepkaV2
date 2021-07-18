package ru.karpyzin.cepka.adapter

import android.util.SparseIntArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList

class HeykaAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    HeykaListItem.DecorationListAdapter {

    private var items = CopyOnWriteArrayList<HeykaListItem>()
    private val mViewTypesPositions = SparseIntArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val posViewHolder: Int = mViewTypesPositions.get(viewType)
        val holderPosition: Int = getPositionByViewHolderPosition(posViewHolder)
        return items[holderPosition].getViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        items[position].onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        val listItem: HeykaListItem? = if (position < itemCount) items[position] else null
        var hash = 0

        if (listItem != null) {
            hash = Objects.hash(listItem.getViewType(), listItem.getViewHolderHash())
        }

        mViewTypesPositions.put(hash, position)
        return hash
    }

    fun setItems(list: List<HeykaListItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getPositionByViewHolderPosition(viewHolderPosition: Int): Int = viewHolderPosition

    override fun getItems(): List<HeykaListItem> = items
}