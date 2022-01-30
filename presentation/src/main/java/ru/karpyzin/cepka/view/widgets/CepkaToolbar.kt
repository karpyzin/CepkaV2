package ru.karpyzin.cepka.view.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.karpyzin.cepka.databinding.WidgetToolbarBinding
import ru.karpyzin.cepka.ext.setDebounceOnClickListener

class CepkaToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {

    var leftListener: (() -> Unit)? = null

    private val binding = WidgetToolbarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.leftIcon.setDebounceOnClickListener {
            leftListener?.invoke()
        }
    }

    fun setTitle(text: String?) {
        binding.toolbarText.text = text
    }
}