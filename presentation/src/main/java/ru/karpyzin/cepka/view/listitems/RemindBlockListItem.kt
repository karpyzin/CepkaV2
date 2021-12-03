package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.HeykaListItem
import ru.karpyzin.cepka.databinding.ListitemRemindBlockBinding
import ru.karpyzin.domain.reminders.Reminder

class RemindBlockListItem(private val reminder: Reminder) : HeykaListItem {
    override fun getViewType(): Int = 7

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        with((holder as ViewHolder).binding) {
            titleTextView.text = reminder.title
            remindDescriptionTextView.text = reminder.description
            timeLeftTextView.text = reminder.date
        }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            ListitemRemindBlockBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private class ViewHolder(val binding: ListitemRemindBlockBinding) : BaseViewHolder(binding.root)
}