package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.HeykaListItem
import ru.karpyzin.cepka.databinding.ListitemCounterBlockBinding

class CounterBlockListItem() : HeykaListItem {
    override fun getViewType(): Int = 8

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as? ViewHolder
        //holder.binding.text.text = data.id.toString()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            ListitemCounterBlockBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private class ViewHolder(val binding: ListitemCounterBlockBinding) :
        BaseViewHolder(binding.root)
}