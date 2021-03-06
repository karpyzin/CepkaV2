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
import ru.karpyzin.domain.account.AccountModel
import ru.karpyzin.domain.account.AccountUseCase
import ru.karpyzin.domain.counter.CounterModel
import ru.karpyzin.domain.counter.CountersUseCase
import ru.karpyzin.domain.hint.HintModel
import ru.karpyzin.domain.hint.HintUseCase
import ru.karpyzin.domain.reminders.ReminderModel
import ru.karpyzin.domain.reminders.RemindersUseCase
import ru.karpyzin.domain.settings.SettingsUseCase

class HomeViewModel @ViewModelInject constructor(
    application: Application,
    private val hintsUseCase: HintUseCase,
    private val countersUseCase: CountersUseCase,
    private val remindersUseCase: RemindersUseCase,
    private val accountUseCase: AccountUseCase,
    private val settingsUseCase: SettingsUseCase
) : BaseViewModel(application) {

    private val timelineManager = TimelineManager()

    private val remindersFlow = remindersUseCase.remindersFlow.map { timelineManager.updateReminders(it) }
    private val hintsFlow = hintsUseCase.hints.map { timelineManager.updateHints(it) }
    private val countersFlow = countersUseCase.counters.map { timelineManager.updateCounters(it) }
    private val accountFlow = accountUseCase.flow.map { timelineManager.updateTop(it) }

    val itemsFlow = combine(accountFlow, hintsFlow, remindersFlow, countersFlow) { account, hints, reminders, counters ->
        val list = mutableListOf<CepkaListItem>()

        list.add(account)
        list.add(hints)
        //list.addAll(timelineManager.updateFinances())
        list.addAll(reminders)
        list.addAll(counters)

        return@combine list
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            if (settingsUseCase.get()?.isFirstRun == true) {
                hintsUseCase.createBaseHints()
                settingsUseCase.firstTimeRan()
            }
        }
    }

    fun addReminder() {
        viewModelScope.launch {
            openScreen.emit(R.id.action_home_to_add_reminder)
        }
    }

    inner class TimelineManager {
        private val reminders = mutableListOf<CepkaListItem>()
        private val counters = mutableListOf<CepkaListItem>()

        //region Listeners

        private val hintListener = object : HintListItem.Listener {
            override fun onClick(hintId: Int) {
                viewModelScope.launch {
                    openScreenWithArgs.emit(HomeFragmentDirections.actionHomeToHint(hintId))
                }
            }

        }

        private val reminderListener = object : RemindBlockListItem.Listener {
            override fun onDoneClick(reminderId: Int) {
                viewModelScope.launch(Dispatchers.IO) {
                    remindersUseCase.complete(reminderId)
                    inAppMessage.emit(InAppMessage("?????? ??????????????!", "?????????????????????? ???????????????????? ?? ?????????? \uD83D\uDCDA"))
                }
            }

            override fun onDismissClick(reminderId: Int) {
                viewModelScope.launch(Dispatchers.IO) {
                    remindersUseCase.snooze(reminderId)
                    inAppMessage.emit(InAppMessage("????????????", "?????????????? ?????????????????????? ???? 1 ?????? ???"))
                }
            }

            override fun onMoreClick(reminderId: Int) {

            }

        }

        private val counterListener = object : CounterBlockListItem.Listener {
            override fun onPlus(counterId: Int) {
                viewModelScope.launch(Dispatchers.IO) {
                    countersUseCase.increaseCount(counterId)
                }
            }

            override fun onMinus(counterId: Int) {
                viewModelScope.launch(Dispatchers.IO) {
                    countersUseCase.decreaseCount(counterId)
                }
            }

        }

        private val headerListener = object : HomeHeaderListItem.Listener {
            override fun onNotificationClick() {
                //viewModelScope.launch { openScreen.emit(R.id.navigation_sign_in) }
            }

            override fun onProfileClick() {
                viewModelScope.launch {
                    val screen = if (accountUseCase.isAuthorized()) R.id.navigation_chat else R.id.navigation_sign_in

                    openScreen.emit(screen)
                }
            }

        }

        //endregion

        fun updateReminders(list: List<ReminderModel>): List<CepkaListItem> {
            reminders.clear()

            //region Reminders
            reminders.takeIf { list.isNotEmpty() }?.add(HomeSubtitleListItem("Reminders"))
            list.forEach { reminder ->
                val item = RemindBlockListItem(reminder)

                item.listener = reminderListener
                reminders.add(item)
            }
            //endregion
            return reminders
        }

        fun updateTop(account: AccountModel?): CepkaListItem {
            return HomeHeaderListItem(account).apply {
                listener = headerListener
            }
        }

        fun updateHints(hints: List<HintModel>): CepkaListItem {
            return HintsBlockListItem(hints, hintListener)
        }

        fun updateCounters(list: List<CounterModel>): List<CepkaListItem> {
            counters.clear()
            list.forEach {
                counters.add(CounterBlockListItem(it).apply {
                    listener = counterListener
                })
            }

            return counters
        }

        fun updateFinances(): List<CepkaListItem> {
            val list = mutableListOf<CepkaListItem>()

            list.add(HomeSubtitleListItem("Finances"))
            list.add(FinanceBlockListItem())

            return list
        }
    }

}