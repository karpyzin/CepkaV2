package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.HeykaListItem
import ru.karpyzin.cepka.databinding.ListitemHomeHeaderBinding

class HomeHeaderListItem : HeykaListItem {
    override fun getViewType(): Int = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemHomeHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemHomeHeaderBinding) : BaseViewHolder(binding.root)
}