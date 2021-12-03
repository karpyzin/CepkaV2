package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaAdapter
import ru.karpyzin.cepka.adapter.HeykaListItem
import ru.karpyzin.cepka.databinding.ListitemBlockBinding

class BlockListItem(@ColorRes private val backgroundColor: Int) : HeykaListItem {
    override fun getViewType(): Int = 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        val context = holder.binding.root.context
        holder.binding.root.backgroundTintList =
            ContextCompat.getColorStateList(context, backgroundColor)
        val adapter = CepkaAdapter()
        val list = mutableListOf<HeykaListItem>()
        list.add(HomeTaskListItem(HomeTaskListItem.Task(1, "Morning energy", true)))
        list.add(HomeTaskListItem(HomeTaskListItem.Task(2, "Clean teeth", true)))
        list.add(HomeTaskListItem(HomeTaskListItem.Task(3, "Workout", true)))
        list.add(HomeTaskListItem(HomeTaskListItem.Task(4, "Sex with girlfriend", false)))
        list.add(HomeTaskListItem(HomeTaskListItem.Task(5, "Sleeping time", true)))
        holder.binding.blockRecyclerView.adapter = adapter
        holder.binding.blockRecyclerView.layoutManager = LinearLayoutManager(
            holder.binding.root.context
        )

        adapter.setItems(list)
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemBlockBinding) : BaseViewHolder(binding.root)
}