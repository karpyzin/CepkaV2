package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaAdapter
import ru.karpyzin.cepka.adapter.HeykaListItem
import ru.karpyzin.cepka.databinding.ListitemHintsBinding
import ru.karpyzin.domain.hint.Hint

class HintsBlockListItem(private val data: Hint) : HeykaListItem {
    override fun getViewType(): Int = 3

    override fun getId(): Long = data.id.hashCode().toLong()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder

        val adapter = CepkaAdapter()
        val list = mutableListOf<HeykaListItem>()
        list.add(HintListItem(Hint(1253)))
        list.add(HintListItem(Hint(56)))
        list.add(HintListItem(Hint(253)))
        list.add(HintListItem(Hint(85)))
        list.add(HintListItem(Hint(3455)))
        list.add(HintListItem(Hint(635675367)))
        holder.binding.hintRecyclerView.adapter = adapter
        holder.binding.hintRecyclerView.layoutManager = LinearLayoutManager(
            holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false
        )

        adapter.setItems(list)
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemHintsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemHintsBinding) : BaseViewHolder(binding.root)
}