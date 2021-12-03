package ru.karpyzin.cepka.adapter

import androidx.recyclerview.widget.RecyclerView

interface Adapter {
    fun deactivateItem(holder: RecyclerView.ViewHolder?)
}