package ru.karpyzin.cepka.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.karpyzin.cepka.MainActivity

@AndroidEntryPoint
abstract class BaseFragment(
    @LayoutRes private val contentLayoutId: Int
) : Fragment(contentLayoutId) {

    protected abstract val binding: ViewBinding

    open val isBottomVisible: Boolean = true
    open val isMainButtonVisible: Boolean = false
    open val mainButtonIconRes: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

        if (resourceId > 0) {
            val statusBarHeight = resources.getDimensionPixelSize(resourceId)
            binding.root.updatePadding(top = statusBarHeight)
        }

        (requireActivity() as? MainActivity)?.let {
            it.updateMainButtonIcon(mainButtonIconRes)
            it.showMainButton(isMainButtonVisible)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    open fun onMainButtonClicked() {}
}