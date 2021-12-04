package ru.karpyzin.cepka.mvvm.premium

import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentPremiumBinding
import ru.karpyzin.cepka.view.viewBinding

class PremiumFragment : BaseFragment(R.layout.fragment_premium) {
    override val binding by viewBinding(FragmentPremiumBinding::bind)
}