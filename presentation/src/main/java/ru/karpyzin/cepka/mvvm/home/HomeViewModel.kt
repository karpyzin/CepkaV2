package ru.karpyzin.cepka.mvvm.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.HeykaListItem
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.view.listitems.*
import ru.karpyzin.domain.hint.Hint
import ru.karpyzin.domain.reminders.Reminder
import ru.karpyzin.domain.reminders.RemindersUseCase
import timber.log.Timber
import java.util.*

class HomeViewModel @ViewModelInject constructor(
    application: Application,
    private val remindersUseCase: RemindersUseCase
) : BaseViewModel(application) {

    private val timelineManager = TimelineManager()

    val itemsFlow = combine(
        remindersUseCase.remindersFlow.distinctUntilChanged()
    ) {
        timelineManager.apply {
            updateReminders(it.first())
            updateTop()
            updateBottom()
        }
        return@combine timelineManager.updateItems()
        //itemsFlow.emit(getReminders(it.first()))
    }

     init {
        Timber.e("asdasasdasd")
    }

    private fun getReminders(reminders: List<Reminder>): List<HeykaListItem> {
        val list = mutableListOf<HeykaListItem>()
        list.add(HomeHeaderListItem())
        list.add(HintsBlockListItem(Hint(123)))
        list.takeIf { reminders.isNotEmpty() }?.add(HomeSubtitleListItem("Reminders"))
        list.addAll(reminders.map { RemindBlockListItem(it) })
        list.add(HomeSubtitleListItem("Today`s plan"))
        list.add(BlockListItem(R.color.red))
        list.add(HomeSubtitleListItem("Cup of water"))
        list.add(CounterBlockListItem())
        return list
    }

    fun getOthers(): List<HeykaListItem> {
        val testList = mutableListOf<HeykaListItem>()
        testList.add(HomeHeaderListItem())
        testList.add(HintsBlockListItem(Hint(123)))
        testList.add(HomeSubtitleListItem("Today`s plan"))
        testList.add(BlockListItem(R.color.orange))
        testList.add(HomeSubtitleListItem("Cup of water"))
        testList.add(CounterBlockListItem())
        return testList
    }

    class TimelineManager {
        private val reminders = mutableListOf<HeykaListItem>()
        private val topMock = mutableListOf<HeykaListItem>()
        private val bottomMock = mutableListOf<HeykaListItem>()

        fun updateReminders(list: List<Reminder>) {
            reminders.clear()
            reminders.takeIf { list.isNotEmpty() }?.add(HomeSubtitleListItem("Reminders"))
            Timber.e("aasdasdasd $list")
            list.forEach { reminder ->
                reminders.add(RemindBlockListItem(reminder))
            }
        }

        fun updateItems(): List<HeykaListItem> {
            val items = mutableListOf<HeykaListItem>()
            items.addAll(topMock)
            items.addAll(reminders)
            items.addAll(bottomMock)
            return items
        }

        fun updateTop() {
            topMock.clear()
            topMock.add(HomeHeaderListItem())
            topMock.add(HintsBlockListItem(Hint(123)))
        }

        fun updateBottom() {
            bottomMock.clear()
            bottomMock.add(HomeSubtitleListItem("Today`s plan"))
            bottomMock.add(BlockListItem(R.color.red))
            bottomMock.add(HomeSubtitleListItem("Cup of water"))
            bottomMock.add(CounterBlockListItem())
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

}