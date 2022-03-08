package ru.karpyzin.cepka.mvvm.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.CepkaAdapter
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentHomeBinding
import ru.karpyzin.cepka.ext.collectAndRepeatWithViewLifecycle
import ru.karpyzin.cepka.ext.collectWhenResumed
import ru.karpyzin.cepka.view.viewBinding
import ru.karpyzin.cepka.view.widgets.inAppMessage

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val adapter by lazy { CepkaAdapter() }
    private val viewModel: HomeViewModel by activityViewModels()

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val isMainButtonVisible: Boolean
        get() = true
    override val mainButtonIconRes: Int
        get() = R.drawable.ic_add

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()

        viewModel.itemsFlow.collectWhenResumed(lifecycleScope) {
            adapter.setItems(it)
        }

        viewModel.inAppMessage.collectWhenResumed(lifecycleScope) {
            requireActivity().inAppMessage(it)
        }

        viewModel.openScreen.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().navigate(it)
        }

        viewModel.openScreenWithArgs.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().navigate(it)
        }

    }

    override fun onMainButtonClicked() {
        viewModel.addReminder()
    }

    private fun initAdapter() {
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}