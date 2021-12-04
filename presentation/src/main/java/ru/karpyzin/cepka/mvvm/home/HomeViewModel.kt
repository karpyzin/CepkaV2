package ru.karpyzin.cepka.mvvm.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.CepkaListItem
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.cepka.view.listitems.*
import ru.karpyzin.cepka.view.widgets.InAppMessage
import ru.karpyzin.domain.hint.HintModel
import ru.karpyzin.domain.hint.HintUseCase
import ru.karpyzin.domain.reminders.ReminderModel
import ru.karpyzin.domain.reminders.RemindersUseCase

class HomeViewModel @ViewModelInject constructor(
    application: Application,
    private val remindersUseCase: RemindersUseCase,
    private val hintUseCase: HintUseCase
) : BaseViewModel(application) {

    val inAppMessage = MutableSharedFlow<InAppMessage>()

    private val timelineManager = TimelineManager()

    private val remindersFlow = remindersUseCase.remindersFlow.map { timelineManager.updateReminders(it) }
    private val hintsFlow = hintUseCase.hints.map { timelineManager.updateTop(it) }

    val itemsFlow = combine(hintsFlow, remindersFlow) { f, s ->
        val list = mutableListOf<CepkaListItem>()
        list.addAll(f)
        list.addAll(s)
        list.addAll(timelineManager.updateCounters())
        return@combine list
    }

    inner class TimelineManager {
        private val reminders = mutableListOf<CepkaListItem>()
        private val topMock = mutableListOf<CepkaListItem>()

        //region Listeners

        private val hintListener = object : HintListItem.Listener {
            override fun onClick(hintId: Int) {
                viewModelScope.launch {
                    openScreen.emit(R.id.navigation_premium)
                }
            }

        }

        private val reminderListener = object : RemindBlockListItem.Listener {
            override fun onDoneClick(reminderId: Int) {
                viewModelScope.launch(Dispatchers.IO) {
                    remindersUseCase.complete(reminderId)
                    inAppMessage.emit(InAppMessage("Так держать!", "Напоминание перенесено в архив \uD83D\uDCDA"))
                }
            }

            override fun onDismissClick(reminderId: Int) {
                viewModelScope.launch(Dispatchers.IO) {
                    remindersUseCase.complete(reminderId)
                    inAppMessage.emit(InAppMessage("Готово", "Перенес напоминание на 15 минут ⏳"))
                }
            }

            override fun onMoreClick(reminderId: Int) {

            }

        }

        private val counterListener = object : CounterBlockListItem.Listener {
            override fun onPlus(counterId: Int) {

            }

            override fun onMinus(counterId: Int) {

            }

        }

        //endregion

        fun updateReminders(list: List<ReminderModel>): List<CepkaListItem> {
            reminders.clear()
            reminders.takeIf { list.isNotEmpty() }?.add(HomeSubtitleListItem("Reminders"))
            list.forEach { reminder ->
                val item = RemindBlockListItem(reminder)

                item.listener = reminderListener
                reminders.add(item)
            }
            return reminders
        }

        fun updateTop(hints: List<HintModel>): List<CepkaListItem> {
            topMock.clear()
            topMock.add(HomeHeaderListItem(HomeHeaderListItem.HeaderData("Игорь", true)))
            topMock.add(HintsBlockListItem(hints, hintListener))
            return topMock
        }

        fun updateCounters(): List<CepkaListItem> {
            val item = CounterBlockListItem(CounterBlockListItem.CounterData(1, 1, 5, R.drawable.ic_cup))
            item.listener = counterListener
            return listOf(item)
        }
    }

}