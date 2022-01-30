package ru.karpyzin.cepka.mvvm.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentProfileBinding
import ru.karpyzin.cepka.ext.collectAndRepeatWithViewLifecycle
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.view.viewBinding

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()

    override val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signOutButton.setDebounceOnClickListener {
            viewModel.onLogoutClick()
        }

        binding.toolbar.setTitle("Profile")
        binding.toolbar.leftListener = {
            findNavController().popBackStack()
        }

        viewModel.backClick.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

}