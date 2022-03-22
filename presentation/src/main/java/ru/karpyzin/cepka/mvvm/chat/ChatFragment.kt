package ru.karpyzin.cepka.mvvm.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentChatBinding
import ru.karpyzin.cepka.ext.collectAndRepeatWithViewLifecycle
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.view.viewBinding

class ChatFragment : BaseFragment(R.layout.fragment_chat) {

    private val viewModel: ChatViewModel by viewModels()

    override val binding by viewBinding(FragmentChatBinding::bind)
    override val isMainButtonVisible: Boolean
        get() = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setTitle("Account")
        binding.toolbar.leftListener = {
            findNavController().popBackStack()
        }


        binding.sendB.setDebounceOnClickListener {
            viewModel.onMessageSend(binding.sendd.text?.toString() ?: "asdasd")
        }

        viewModel.backClick.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }
}