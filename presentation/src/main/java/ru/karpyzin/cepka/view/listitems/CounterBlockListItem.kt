package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemCounterBlockBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener

class CounterBlockListItem(private val data: CounterData) : CepkaListItem {

    interface Listener {
        fun onPlus(counterId: Int)
        fun onMinus(counterId: Int)
    }

    data class CounterData(
        val id: Int,
        val counter: Int,
        val maxValue: Int,
        @DrawableRes val icon: Int
    )

    var listener: Listener? = null

    override fun getViewType(): Int = 8

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with((holder as ViewHolder).binding) {
        val context = root.context

        counter.text = "${data.counter}/${data.maxValue}"
        counterIcon.setImageDrawable(ContextCompat.getDrawable(context, data.icon))
        counterMinusButton.setDebounceOnClickListener {
            listener?.onMinus(data.id)
        }
        counterPlusButton.setDebounceOnClickListener {
            listener?.onPlus(data.id)
        }
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