package ru.karpyzin.cepka.mvvm.reminder

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
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
import java.util.*

class ReminderFragment : BaseFragment(R.layout.fragment_reminder) {

    private val viewModel: ReminderViewModel by viewModels()

    private var timePicker: MaterialTimePicker = MaterialTimePicker.Builder()
        .setTimeFormat(CLOCK_24H)
        .setHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
        .setMinute(Calendar.getInstance().get(Calendar.MINUTE))
        .build()
    private var datePicker = MaterialDatePicker.Builder.datePicker().build()
    private val adapter by lazy { CepkaAdapter() }

    override val binding by viewBinding(FragmentReminderBinding::bind)

    override val isMainButtonVisible: Boolean
        get() = true
    override val mainButtonIconRes: Int
        get() = R.drawable.ic_save

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        binding.reminderTitleEditText.requestFocus()

        binding.timeLayout.setDebounceOnClickListener {
            timePicker.show(childFragmentManager, "")
        }

        binding.dateLayout.setDebounceOnClickListener {
            datePicker.show(childFragmentManager, "")
        }
        datePicker.addOnPositiveButtonClickListener {
            viewModel.changeDate(it)
        }
        timePicker.addOnPositiveButtonClickListener {
            viewModel.changeTime(timePicker.hour, timePicker.minute)
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

    private fun initAdapter() {
        binding.categories.adapter = adapter
        binding.categories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
    }
}