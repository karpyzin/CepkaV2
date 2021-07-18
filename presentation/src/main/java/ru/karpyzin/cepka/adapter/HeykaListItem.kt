package ru.karpyzin.cepka.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface HeykaListItem {
    fun getViewType(): Int
    fun getId(): Long = RecyclerView.NO_ID

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    fun getViewHolderHash(): Int = 0

    interface DecorationListAdapter {
        fun getPositionByViewHolderPosition(viewHolderPosition: Int): Int
        fun getItems(): List<HeykaListItem>
    }
}