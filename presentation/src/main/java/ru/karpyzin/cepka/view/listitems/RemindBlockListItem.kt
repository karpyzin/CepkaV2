package ru.karpyzin.cepka.view.listitems

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemRemindBlockBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.domain.reminders.ReminderModel
import java.text.SimpleDateFormat
import java.util.*

class RemindBlockListItem(private val data: ReminderModel) : CepkaListItem {

    interface Listener {
        fun onDoneClick(reminderId: Int)
        fun onDismissClick(reminderId: Int)
        fun onMoreClick(reminderId: Int)
    }

    var listener: Listener? = null

    override fun getId(): Long = data.id.toLong()

    override fun getViewType(): Int = 7

    override fun getViewHolderHash(): Int {
        return Objects.hash(data.date, data.description, data.id, data.title)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with((holder as ViewHolder).binding) {
        titleTextView.text = data.title
        remindDescriptionTextView.text = data.description
        val minutesToLeft = (data.date - System.currentTimeMillis()) / (60 * 1000)
        val time = when {
            DateUtils.isToday(data.date) && minutesToLeft < 60 -> "In $minutesToLeft minutes"
            DateUtils.isToday(data.date) -> "today at ${SimpleDateFormat("hh:mm", Locale.getDefault()).format(data.date)}"
            else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(data.date)
        }
        timeLeftTextView.text = time

        doneButton.setDebounceOnClickListener {
            listener?.onDoneClick(data.id)
        }
        cancelButton.setDebounceOnClickListener {
            listener?.onDismissClick(data.id)
        }
        remindMoreButton.setDebounceOnClickListener {
            listener?.onMoreClick(data.id)
        }
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