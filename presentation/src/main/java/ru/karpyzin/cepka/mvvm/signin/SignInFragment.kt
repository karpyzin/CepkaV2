package ru.karpyzin.cepka.mvvm.signin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentSignInBinding
import ru.karpyzin.cepka.ext.collectAndRepeatWithViewLifecycle
import ru.karpyzin.cepka.ext.collectWhenCreated
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.view.viewBinding
import ru.karpyzin.cepka.view.widgets.inAppMessage

class SignInFragment : BaseFragment(R.layout.fragment_sign_in) {

    private val viewModel: SignInViewModel by viewModels()

    private var isSignUp: Boolean = false

    override val binding by viewBinding(FragmentSignInBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInTitle.text = signInText(false)
        binding.signInButton.text = signInText()

        binding.signInEmail.requestFocus()

        binding.signUpButton.setDebounceOnClickListener {
            isSignUp = !isSignUp
            binding.signInTitle.text = signInText(false)
            binding.signUpButton.text = signUpText()
            binding.signInButton.text = signInText()
        }

        binding.signInButton.setDebounceOnClickListener {
            val email = binding.signInEmail.text?.toString() ?: return@setDebounceOnClickListener
            val pass = binding.signInPassword.text?.toString() ?: return@setDebounceOnClickListener

            viewModel.onAuthClick(email, pass, isSignUp)
        }

        viewModel.inAppMessage.collectWhenCreated(lifecycleScope) {
            requireActivity().inAppMessage(it)
        }

        viewModel.backClick.collectAndRepeatWithViewLifecycle(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun signInText(simple: Boolean = true): String {
        return if (isSignUp) {
            "Sign Up" + if (!simple) " \uD83D\uDE0E" else ""
        } else {
            "Sign In" + if (!simple) " \uD83D\uDC4B" else ""
        }
    }

    private fun signUpText(): String = if (isSignUp) "Sign In" else "Create account"
}