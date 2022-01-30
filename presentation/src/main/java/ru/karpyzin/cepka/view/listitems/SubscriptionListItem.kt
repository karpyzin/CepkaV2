package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemRemindBlockBinding
import ru.karpyzin.cepka.databinding.ListitemSubscriptionBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.domain.reminders.ReminderModel
import ru.karpyzin.domain.subscriptions.SubscriptionModel
import java.util.*

class SubscriptionListItem(private val data: SubscriptionModel) : CepkaListItem {

    interface Listener {
        fun onDoneClick(reminderId: Int)
        fun onDismissClick(reminderId: Int)
        fun onMoreClick(reminderId: Int)
    }

    var listener: Listener? = null

    override fun getId(): Long = data.id.toLong()

    override fun getViewType(): Int = 8

    override fun getViewHolderHash(): Int {
        return Objects.hash(data.id, data.description, data.name, data.repeatDay, data.currency)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with((holder as ViewHolder).binding) {
        subscriptionName.text = data.name
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            ListitemSubscriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private class ViewHolder(val binding: ListitemSubscriptionBinding) : BaseViewHolder(binding.root)
}