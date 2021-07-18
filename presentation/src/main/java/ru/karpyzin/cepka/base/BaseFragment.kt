package ru.karpyzin.cepka.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment(
    @LayoutRes private val contentLayoutId: Int
) : Fragment(contentLayoutId) {

    protected abstract val binding: ViewBinding
}