package ru.karpyzin.cepka.mvvm.reminder

import android.app.Application
import android.text.format.DateUtils
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.karpyzin.cepka.base.BaseViewModel
import ru.karpyzin.domain.reminders.RemindersUseCase
import java.text.SimpleDateFormat
import java.util.*

class ReminderViewModel @ViewModelInject constructor(
    application: Application,
    private val remindersUseCase: RemindersUseCase
) : BaseViewModel(application) {

    val reminderFlow = MutableStateFlow(Reminder("", "", "", ""))

    private var date = CalendarDate(0, 0, 0, 0, 0, 0)
    private var title: String = ""
    private var description: String? = null
    private val ct = Calendar.getInstance()

    init {
        ct.add(Calendar.HOUR, 1)
        date = date.copy(
            year = ct[Calendar.YEAR],
            month = ct[Calendar.MONTH],
            day = ct[Calendar.DAY_OF_MONTH],
            hour = ct[Calendar.HOUR_OF_DAY],
            minute = ct[Calendar.MINUTE],
            millis = ct.timeInMillis
        )

        updateTime()
    }

    fun changeTime(hour: Int, minute: Int) {
        ct.set(date.year, date.month, date.day, hour, minute)
        date = date.copy(hour = hour, minute = minute, millis = ct.timeInMillis)

        updateTime()
    }

    fun changeDate(year: Int, month: Int, day: Int) {
        ct.set(year, month, day, date.hour, date.minute)
        date = date.copy(year = year, month = month, day = day, millis = ct.timeInMillis)

        updateTime()
    }

    fun changeTitle(data: String) {
        title = data
    }

    fun changeDescription(data: String?) {
        description = data
    }

    fun createReminder() = viewModelScope.launch(Dispatchers.IO) {
        remindersUseCase.add(title, description, date.millis)
        backClick.emit(true)
    }

    private fun updateTime() {
        val date = when {
            DateUtils.isToday(ct.timeInMillis) -> "today"
            else -> SimpleDateFormat("dd MMM", Locale.getDefault()).format(ct.timeInMillis)
        }
        val time = SimpleDateFormat("hh:mm", Locale.getDefault()).format(ct.timeInMillis)

        viewModelScope.launch {
            reminderFlow.emit(Reminder(title, description, time, date, this@ReminderViewModel.date))
        }
    }

    data class CalendarDate(
        val year: Int,
        val month: Int,
        val day: Int,
        val hour: Int,
        val minute: Int,
        val millis: Long
    )

    data class Reminder(
        val title: String,
        val description: String?,
        val time: String,
        val date: String,
        val calendar: CalendarDate? = null
    )
}