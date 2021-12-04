package ru.karpyzin.cepka.view.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class CepkaRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {


    private val animator: DefaultItemAnimator = object : DefaultItemAnimator() {
        override fun canReuseUpdatedViewHolder(viewHolder: ViewHolder): Boolean {
            return true
        }
    }

    init {
        itemAnimator = animator
        //edgeEffectFactory = EdgeEffectFactory()
    }

}