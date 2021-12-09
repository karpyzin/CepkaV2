package ru.karpyzin.cepka.mvvm.subscriptions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.CepkaAdapter
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentSubscriptionsBinding
import ru.karpyzin.cepka.ext.collectWhenResumed
import ru.karpyzin.cepka.ext.collectWhenStarted
import ru.karpyzin.cepka.view.viewBinding

class SubscriptionsFragment : BaseFragment(R.layout.fragment_subscriptions) {

    private val adapter by lazy { CepkaAdapter() }
    private val viewModel: SubscriptionsViewModel by activityViewModels()

    override val binding by viewBinding(FragmentSubscriptionsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.itemsFlow.collectWhenResumed(lifecycleScope) {
            adapter.setItems(it)
        }
    }

    private fun initAdapter() {
        binding.root.adapter = adapter
        binding.root.layoutManager = LinearLayoutManager(requireContext())
    }
}