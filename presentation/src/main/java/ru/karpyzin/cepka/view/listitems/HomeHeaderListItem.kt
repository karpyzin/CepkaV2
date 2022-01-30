package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemHomeHeaderBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener

class HomeHeaderListItem(private val data: HeaderData) : CepkaListItem {

    interface Listener {
        fun onNotificationClick()
        fun onProfileClick()
    }

    data class HeaderData(
        val name: String?,
        val hasNotifications: Boolean
    )

    var listener: Listener? = null

    override fun getViewType(): Int = 4

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) = with((holder as ViewHolder)) {
        val context = binding.root.context
        val headerText = if (data.name != null) {
            context.getString(R.string.header_username, data.name)
        } else {
            context.getString(R.string.header_username_default)
        }

        binding.notificationIndicator.isVisible = data.hasNotifications
        binding.name.text = headerText
        binding.name.setDebounceOnClickListener {
            listener?.onProfileClick()
        }
        binding.notificationButton.setDebounceOnClickListener {
            listener?.onNotificationClick()
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemHomeHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemHomeHeaderBinding) : BaseViewHolder(binding.root)
}