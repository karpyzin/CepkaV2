package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaAdapter
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemHintsBinding
import ru.karpyzin.domain.hint.HintModel
import java.util.*

class HintsBlockListItem(private val data: List<HintModel>, val listener: HintListItem.Listener) : CepkaListItem {

    private val adapter by lazy { CepkaAdapter() }

    override fun getViewType(): Int = 3

    override fun getViewHolderHash(): Int {
        return Objects.hash(data)
    }

    override fun getId(): Long = data.hashCode().toLong()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder

        val list = mutableListOf<CepkaListItem>()
        data.forEach {
            val item = HintListItem(it)
            item.listener = listener
            list.add(item)
        }

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