package ru.karpyzin.cepka.mvvm.hint

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentHintBinding
import ru.karpyzin.cepka.ext.collectWhenCreated
import ru.karpyzin.cepka.view.viewBinding
import timber.log.Timber

class HintFragment : BaseFragment(R.layout.fragment_hint) {

    private val args: HintFragmentArgs by navArgs()

    override val binding: FragmentHintBinding by viewBinding(FragmentHintBinding::bind)

    private val viewModel: HintViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadHint(args.hintId)

        viewModel.hintFlow.collectWhenCreated(lifecycleScope) {
            Glide.with(requireContext()).load(it?.contentData?.primaryImageUri).into(binding.hintImage)
            binding.hintPrimaryText.text = it?.primaryText
            binding.hintSecondaryText.text = it?.contentData?.text
        }
    }

}