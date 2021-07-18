package ru.karpyzin.cepka.mvvm.subscriptions

import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentSubscriptionsBinding
import ru.karpyzin.cepka.view.viewBinding

class SubscriptionsFragment : BaseFragment(R.layout.fragment_subscriptions) {
    override val binding by viewBinding(FragmentSubscriptionsBinding::bind)
}