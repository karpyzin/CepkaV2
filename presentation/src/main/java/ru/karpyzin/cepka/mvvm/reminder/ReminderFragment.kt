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
import androidx.recyclerview.widget.LinearLayoutManager
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.CepkaAdapter
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentReminderBinding
import ru.karpyzin.cepka.ext.collectAndRepeatWithViewLifecycle
import ru.karpyzin.cepka.ext.collectWhenCreated
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.ext.showKeyboard
import ru.karpyzin.cepka.view.viewBinding
import ru.karpyzin.cepka.view.widgets.inAppMessage

class ReminderFragment : BaseFragment(R.layout.fragment_reminder), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private val viewModel: ReminderViewModel by viewModels()

    private var timePicker: TimePickerDialog? = null
    private var datePicker: DatePickerDialog? = null
    private val adapter by lazy { CepkaAdapter() }

    override val binding by viewBinding(FragmentReminderBinding::bind)

    override val isMainButtonVisible: Boolean
        get() = true
    override val mainButtonIconRes: Int
        get() = R.drawable.ic_save

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        timePicker = TimePickerDialog(requireContext(), this, 0, 0, true)
        datePicker = DatePickerDialog(requireContext(), this, 0, 0, 0)

        binding.reminderTitleEditText.requestFocus()

        binding.timeTextView.setDebounceOnClickListener {
            timePicker?.show()
        }

        binding.dateTextView.setDebounceOnClickListener {
            datePicker?.show()
        }

        binding.reminderTitleEditText.addTextChangedListener {
            viewModel.changeTitle(it?.toString())
        }

        binding.reminderDescriptionEditText.addTextChangedListener {
            viewModel.changeDescription(it?.toString())
        }

        binding.toolbar.setTitle("Add reminder")
        binding.toolbar.leftListener = {
            findNavController().popBackStack()
        }

        binding.reminderTitleEditText.showKeyboard()

        viewModel.reminderFlow.collectWhenCreated(lifecycleScope) {
            binding.dateTextView.text = it.date
            binding.timeTextView.text = it.time
            binding.reminderTitleEditText.setText(it.title)
            binding.reminderDescriptionEditText.setText(it.description)
            timePicker?.updateTime(it.calendar?.hour ?: 0, it.calendar?.minute ?: 0)
            datePicker?.updateDate(it.calendar?.year ?: 0, it.calendar?.month ?: 0, it.calendar?.day ?: 0)
        }

        viewModel.categoriesFlow.collectWhenCreated(lifecycleScope) {
            adapter.setItems(it)
        }

        viewModel.backClick.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().popBackStack()
        }

        viewModel.inAppMessage.collectWhenCreated(lifecycleScope) {
            requireActivity().inAppMessage(it)
        }
    }

    override fun onMainButtonClicked() {
        viewModel.createReminder()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        viewModel.changeDate(year, month, dayOfMonth)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        viewModel.changeTime(hourOfDay, minute)
    }

    private fun initAdapter() {
        binding.categories.adapter = adapter
        binding.categories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}