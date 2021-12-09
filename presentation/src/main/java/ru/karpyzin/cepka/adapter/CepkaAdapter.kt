package ru.karpyzin.cepka.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.CopyOnWriteArrayList

class CepkaAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), CepkaListItem.DecorationListAdapter, Adapter {

    private var items = CopyOnWriteArrayList<CepkaListItem>()
    private val mViewTypesPositions = hashMapOf<Int, Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val position: Int = mViewTypesPositions[viewType] ?: 0
        return items[position].getViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val cepkaListItem: CepkaListItem = items[position]
        cepkaListItem.onBindViewHolder(holder, position)
    }

    override fun deactivateItem(holder: RecyclerView.ViewHolder?) {
        if (holder == null) {
            return
        }

        /*val listItem: CepkaListItem = items[holder.adapterPosition]
        listItem.deactivate(holder, holder.adapterPosition)*/
    }

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        deactivateItem(holder)
    }

    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].getId()

    override fun getItemViewType(position: Int): Int {
        val listItem: CepkaListItem = items[position]
        mViewTypesPositions[listItem.getViewType()] = position
        return listItem.getViewType()
    }

    fun setItems(list: List<CepkaListItem>) {
        val diffCallback = CepkaDiffUtil(list, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        items.clear()
        items.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItems(): List<CepkaListItem> = items

    inner class CepkaDiffUtil(
        private val newList: List<CepkaListItem>,
        private val oldList: List<CepkaListItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].getId() == newList[newItemPosition].getId()
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].getViewHolderHash() == newList[newItemPosition].getViewHolderHash()
        }

    }
}