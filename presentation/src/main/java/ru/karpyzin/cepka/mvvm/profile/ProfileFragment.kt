package ru.karpyzin.cepka.mvvm.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.karpyzin.cepka.MainActivity
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentProfileBinding
import ru.karpyzin.cepka.ext.collectAndRepeatWithViewLifecycle
import ru.karpyzin.cepka.ext.collectWhenCreated
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.view.viewBinding
import ru.karpyzin.cepka.view.widgets.inAppMessage

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override val binding by viewBinding(FragmentProfileBinding::bind)

    override val isBottomVisible: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signOutButton.setDebounceOnClickListener {
            viewModel.onLogoutClick()
        }

        binding.changeButton.setDebounceOnClickListener {
            viewModel.updateName(binding.profileName.text?.toString())
        }

        binding.toolbar.setTitle("Profile")
        binding.toolbar.leftListener = {
            findNavController().popBackStack()
        }

        viewModel.backClick.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.accountFlow.collectWhenCreated(lifecycleScope) {
            binding.profileName.setText(it?.name)
        }
        viewModel.inAppMessage.collectWhenCreated(lifecycleScope) {
            requireActivity().inAppMessage(it)
        }
    }

}