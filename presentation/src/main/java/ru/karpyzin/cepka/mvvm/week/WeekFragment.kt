package ru.karpyzin.cepka.mvvm.week

import androidx.viewbinding.ViewBinding
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentWeekBinding
import ru.karpyzin.cepka.view.viewBinding

class WeekFragment: BaseFragment(R.layout.fragment_week) {
    override val binding by viewBinding(FragmentWeekBinding::bind)
}