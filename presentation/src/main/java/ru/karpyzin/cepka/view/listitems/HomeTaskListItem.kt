package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemHomeTaskBinding

class HomeTaskListItem(private val task: Task) : CepkaListItem {
    override fun getViewType(): Int = 6

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder

        holder.binding.taskTitle.text = task.name
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            ListitemHomeTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    private class ViewHolder(val binding: ListitemHomeTaskBinding) : BaseViewHolder(binding.root)

    data class Task(
        val id: Int,
        val name: String,
        val competed: Boolean
    )
}