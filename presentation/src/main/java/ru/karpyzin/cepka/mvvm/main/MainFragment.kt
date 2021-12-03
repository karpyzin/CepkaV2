package ru.karpyzin.cepka.mvvm.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentMainBinding
import ru.karpyzin.cepka.view.viewBinding

class MainFragment : BaseFragment(R.layout.fragment_main) {
    override val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}