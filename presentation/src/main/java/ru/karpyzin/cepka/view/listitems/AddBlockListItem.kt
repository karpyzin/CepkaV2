package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemAddBlockBinding
import ru.karpyzin.cepka.databinding.ListitemCounterBlockBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.domain.counter.CounterModel
import java.util.*

class AddBlockListItem : CepkaListItem {

    interface Listener {
        fun onAddTaskClick()
        fun onAddReminderClick()
    }

    var listener: Listener? = null

    override fun getViewType(): Int = 10

    override fun getViewHolderHash(): Int = 10

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with((holder as ViewHolder).binding) {
        addReminderButton.setDebounceOnClickListener {
            listener?.onAddReminderClick()
        }

        addTaskButton.setDebounceOnClickListener {
            listener?.onAddTaskClick()
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemAddBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemAddBlockBinding) : BaseViewHolder(binding.root)
}