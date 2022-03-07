package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemFinanceBlockBinding
import ru.karpyzin.cepka.databinding.ListitemHintBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.domain.hint.HintModel
import java.util.*

class FinanceBlockListItem : CepkaListItem {

    override fun getViewType(): Int = 12

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with(holder as ViewHolder) {

    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemFinanceBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemFinanceBlockBinding) : BaseViewHolder(binding.root)
}