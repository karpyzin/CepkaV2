package ru.karpyzin.cepka.mvvm.hint

import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentHintBinding
import ru.karpyzin.cepka.view.viewBinding

class HintFragment : BaseFragment(R.layout.fragment_hint) {
    override val binding: FragmentHintBinding by viewBinding(FragmentHintBinding::bind)

    override val isMainButtonVisible: Boolean
        get() = true
    override val mainButtonIconRes: Int
        get() = R.drawable.ic_edit
}