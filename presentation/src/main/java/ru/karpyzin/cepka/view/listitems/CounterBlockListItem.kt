package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemCounterBlockBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.domain.counter.CounterModel
import java.util.*

class CounterBlockListItem(private val data: CounterModel) : CepkaListItem {

    interface Listener {
        fun onPlus(counterId: Int)
        fun onMinus(counterId: Int)
    }

    var listener: Listener? = null

    override fun getViewType(): Int = 9

    override fun getViewHolderHash(): Int {
        return Objects.hash(data.id, data.count, data.primaryText, data.maxValue)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with((holder as ViewHolder).binding) {

        counterCurrent.text = data.count.toString()
        counterMax.text = data.maxValue.toString()
        holder.bindImage(data.iconType)

        counterMinusButton.setOnClickListener {
            if (data.count > 0) listener?.onMinus(data.id)
        }
        counterPlusButton.setOnClickListener {
            listener?.onPlus(data.id)
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemCounterBlockBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemCounterBlockBinding) : BaseViewHolder(binding.root) {

        fun bindImage(type: Int) {
            binding.counterIcon.setImageDrawable(ContextCompat.getDrawable(context, getImage(type)))
        }

        private fun getImage(type: Int): Int {
            return when (type) {
                CounterModel.TYPE_WATER -> R.drawable.ic_cup
                else -> R.drawable.ic_cup
            }
        }
    }
}