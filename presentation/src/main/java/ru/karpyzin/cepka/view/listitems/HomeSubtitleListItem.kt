package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemHomeSubtitleBinding

class HomeSubtitleListItem(private val title: String) : CepkaListItem {
    override fun getViewType(): Int = 5

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.binding.titleTextView.text = title
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            ListitemHomeSubtitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private class ViewHolder(val binding: ListitemHomeSubtitleBinding) : BaseViewHolder(binding.root)
}