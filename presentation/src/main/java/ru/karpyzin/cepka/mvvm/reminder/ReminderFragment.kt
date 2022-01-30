package ru.karpyzin.cepka.mvvm.reminder

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentReminderBinding
import ru.karpyzin.cepka.ext.collectAndRepeatWithViewLifecycle
import ru.karpyzin.cepka.ext.collectWhenCreated
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.view.viewBinding

class ReminderFragment : BaseFragment(R.layout.fragment_reminder), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private val viewModel: ReminderViewModel by viewModels()

    private var timePicker: TimePickerDialog? = null
    private var datePicker: DatePickerDialog? = null

    override val binding by viewBinding(FragmentReminderBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timePicker = TimePickerDialog(requireContext(), this, 0, 0, true)
        datePicker = DatePickerDialog(requireContext(), this, 0, 0, 0)

        binding.reminderTitleEditText.requestFocus()

        viewModel.reminderFlow.collectWhenCreated(lifecycleScope) {
            binding.dateTextView.text = it.date
            binding.timeTextView.text = it.time
            binding.reminderTitleEditText.setText(it.title)
            binding.reminderDescriptionEditText.setText(it.description)
            timePicker?.updateTime(it.calendar?.hour ?: 0, it.calendar?.minute ?: 0)
            datePicker?.updateDate(it.calendar?.year ?: 0, it.calendar?.month ?: 0, it.calendar?.day ?: 0)
        }

        viewModel.backClick.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        binding.timeTextView.setDebounceOnClickListener {
            timePicker?.show()
        }

        binding.dateTextView.setDebounceOnClickListener {
            datePicker?.show()
        }

        binding.createButton.setDebounceOnClickListener {
            viewModel.createReminder()
        }

        binding.reminderTitleEditText.addTextChangedListener {
            viewModel.changeTitle(it?.toString() ?: "")
        }

        binding.reminderDescriptionEditText.addTextChangedListener {
            viewModel.changeDescription(it?.toString())
        }

        binding.createButton.setDebounceOnClickListener {
            if (binding.reminderTitleEditText.text != null) {
                viewModel.createReminder()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.changeDate(year, month, dayOfMonth)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        viewModel.changeTime(hourOfDay, minute)
    }
}