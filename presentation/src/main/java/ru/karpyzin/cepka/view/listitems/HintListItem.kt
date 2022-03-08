package ru.karpyzin.cepka.view.listitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.karpyzin.cepka.adapter.BaseViewHolder
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.databinding.ListitemHintBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.domain.hint.HintModel
import java.util.*

data class HintListItem(private val data: HintModel) : CepkaListItem {

    interface Listener {
        fun onClick(hintId: Int)
    }

    var listener: Listener? = null

    override fun getViewType(): Int = 2

    override fun getId(): Long = data.id.hashCode().toLong()

    override fun getViewHolderHash(): Int {
        return Objects.hash(data.id, data.primaryText, data.secondaryText)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit = with(holder as ViewHolder) {
        binding.hintPrimaryText.text = data.primaryText
        binding.hintSecondaryText.text = data.secondaryText
        binding.root.setDebounceOnClickListener {
            listener?.onClick(data.id)
        }

        Glide.with(binding.root).load(data.contentData?.primaryImageUri).into(binding.hintImage)
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(ListitemHintBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    private class ViewHolder(val binding: ListitemHintBinding) : BaseViewHolder(binding.root)
}