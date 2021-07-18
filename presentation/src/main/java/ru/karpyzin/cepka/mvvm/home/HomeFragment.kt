package ru.karpyzin.cepka.mvvm.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.adapter.HeykaAdapter
import ru.karpyzin.cepka.adapter.HeykaListItem
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.FragmentHomeBinding
import ru.karpyzin.cepka.view.listitems.HintListItem
import ru.karpyzin.cepka.view.viewBinding
import ru.karpyzin.domain.hint.Hint

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    override val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HeykaAdapter()
        binding.homeRecyclerView.adapter = adapter
        binding.homeRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        val testList = mutableListOf<HeykaListItem>()
        testList.add(HintListItem(Hint(6)))
        testList.add(HintListItem(Hint(1)))
        testList.add(HintListItem(Hint(9)))
        testList.add(HintListItem(Hint(6)))
        testList.add(HintListItem(Hint(1)))
        testList.add(HintListItem(Hint(9)))
        testList.add(HintListItem(Hint(6)))
        testList.add(HintListItem(Hint(1)))
        testList.add(HintListItem(Hint(9)))
        testList.add(HintListItem(Hint(6)))
        testList.add(HintListItem(Hint(1)))
        testList.add(HintListItem(Hint(9)))
        testList.add(HintListItem(Hint(6)))
        testList.add(HintListItem(Hint(1)))
        testList.add(HintListItem(Hint(9)))
        testList.add(HintListItem(Hint(3)))
        testList.add(HintListItem(Hint(6)))
        testList.add(HintListItem(Hint(1)))
        testList.add(HintListItem(Hint(9)))
        adapter.setItems(testList)
    }
}