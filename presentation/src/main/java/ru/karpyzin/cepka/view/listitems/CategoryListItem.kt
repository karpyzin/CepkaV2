package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemCategoryBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.view.animation.HeartbeatAnimation
import ru.karpyzin.domain.categories.CategoryModel
import java.util.*

class CategoryListItem(private val data: CategoryModel, private val isSelected: Boolean) : CepkaListItem {

    var listener: ((id: Int) -> Unit)? = null

    override fun getViewType(): Int = 11

    override fun getViewHolderHash(): Int {
        return Objects.hash(data.name, data.id, data.icon, isSelected)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder

        val background = if (isSelected) {
            R.color.accent_red2
        } else {
            R.color.counter_background
        }

        if (isSelected) {
            HeartbeatAnimation().animate(holder.binding.emoji)
        }

        holder.binding.emoji.text = data.icon
        holder.binding.root.setDebounceOnClickListener {
            listener?.invoke(data.id)
        }
        holder.binding.root.setCardBackgroundColor(ContextCompat.getColorStateList(holder.itemView.context, background))
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemCategoryBinding) : BaseViewHolder(binding.root)
}