package ru.karpyzin.cepka.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.karpyzin.cepka.R

@AndroidEntryPoint
abstract class BaseFragment(
    @LayoutRes private val contentLayoutId: Int
) : Fragment(contentLayoutId) {

    protected abstract val binding: ViewBinding

    open val isBottomVisible: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

        if (resourceId > 0) {
            val statusBarHeight = resources.getDimensionPixelSize(resourceId)
            binding.root.updatePadding(top = statusBarHeight)
        }

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigation).visibility = if (isBottomVisible) View.VISIBLE else View.GONE
        super.onViewCreated(view, savedInstanceState)
    }
}